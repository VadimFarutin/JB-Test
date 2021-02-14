#pragma once

#include <optional>
#include <memory>
#include <atomic>

namespace task {

    template<typename T>
    class lock_free_stack {
    public:
        // Конструктор по умолчанию, создающий пустой стек.
        // Для простоты копирование и перемещение стека запрещено. 
        lock_free_stack() 
            : _head(nullptr), _to_delete(nullptr) {}

        // Деструктор.
        ~lock_free_stack() {
            Node<T>* cur_node = _head.load();
            while (cur_node != nullptr) {
                Node<T>* last_node = cur_node;
                cur_node = cur_node->next;
                delete last_node;
            }

            Node<Node<T>*>* cur_node_ptr = _to_delete.load();
            while (cur_node_ptr != nullptr) {
                Node<Node<T>*>* last_node = cur_node_ptr;
                cur_node_ptr = cur_node_ptr->next;
                delete last_node->data;
                delete last_node;
            }
        }

        // Добавление нового элемента в стек. 
        // Клиент передает владение элементом структуре данных.
        void push(T&& value) {
            Node<T>* new_node = new Node<T>(std::move(value));
            Node<T>* old_head = _head.load();

            do {
                new_node->next = old_head;
            } while (!_head.compare_exchange_strong(old_head, new_node));
        }

        // Выталкивание элемента из стека.
        // Структура данных передает владение элементом клиенту.
        std::optional<T> pop() {
            Node<T>* old_head = _head.load();

            do {
                if (old_head == nullptr) {
                    return {};
                }
            } while (!_head.compare_exchange_strong(old_head, old_head->next));

            deferred_delete(old_head);
            return std::move(old_head->data);
        }

    private:
        template<typename S>
        struct Node {
            Node(S& value)
                : data(value), next(nullptr) {}

            Node(S&& value)
                : data(value), next(nullptr) {}

            Node<S>* next;
            S data;
        };

        void deferred_delete(Node<T>* node) {
            Node<Node<T>*>* new_node = new Node<Node<T>*>(node);
            Node<Node<T>*>* old_head = _to_delete.load();

            do {
                new_node->next = old_head;
            } while (!_to_delete.compare_exchange_strong(old_head, new_node));
        }

        std::atomic<Node<T>*> _head;
        std::atomic<Node<Node<T>*>*> _to_delete;
    };

}
