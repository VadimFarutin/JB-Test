#include "lock_free_stack.hpp"

#include <cassert>
#include <vector>
#include <string>
#include <thread>
#include <unordered_set>

namespace tests {
	// Single threaded tests.
	void test_st_empty() {
		task::lock_free_stack<int> stack;
		std::optional<int> result = stack.pop();

		assert(!result.has_value());
	}

	void test_st_one_value() {
		int value = 42;
		int expected = value;
		
		task::lock_free_stack<int> stack;
		stack.push(std::move(value));
		std::optional<int> result = stack.pop();

		assert(result.has_value());
		assert(result.value() == expected);
	}

	void test_st_many_values() {
		std::vector<std::string> values = { "1", "2", "3" };
		std::vector<std::string> expected(values);

		task::lock_free_stack<std::string> stack;
		for (auto value : values) {
			stack.push(std::move(value));
		}
		std::vector<std::optional<std::string>> results;
		while (true) {
			auto result = stack.pop();
			if (!result.has_value()) {
				break;
			}
			results.push_back(result);
		}

		assert(results.size() == expected.size());
		auto result_it = results.begin();
		auto expected_it = expected.rbegin();
		for (; 
			result_it != results.end() && expected_it != expected.rend();
			++result_it, ++expected_it)
		{
			assert(result_it->has_value());
			assert(result_it->value() == *expected_it);
		}
	}

	void test_st_random_order() {
		std::vector<std::string> values = { "1", "2", "3" };
		std::vector<std::string> expected = { "1", "3", "2"};

		task::lock_free_stack<std::string> stack;
		std::vector<std::optional<std::string>> results;
		
		stack.push(std::move(values[0]));
		results.push_back(stack.pop());
		stack.push(std::move(values[1]));
		stack.push(std::move(values[2]));
		results.push_back(stack.pop());
		results.push_back(stack.pop());

		assert(results.size() == expected.size());
		auto result_it = results.begin();
		auto expected_it = expected.begin();
		for (;
			result_it != results.end() && expected_it != expected.end();
			++result_it, ++expected_it) 
		{
			assert(result_it->has_value());
			assert(result_it->value() == *expected_it);
		}
	}

	// Multi threaded tests.
	template<typename T>
	void push_values(
		task::lock_free_stack<T>& stack,
		std::vector<T>& values)
	{
		for (auto value : values) {
			stack.push(std::move(value));
		}
	}

	template<typename T>
	void pop_values(
		task::lock_free_stack<T>& stack,
		std::vector<std::optional<T>>& values,
		size_t n)
	{
		for (size_t i = 0; i < n; ++i) {
			values.push_back(stack.pop());
		}
	}

	void test_mt_push() {
		const size_t n = 1000;
		std::vector<int> values1;
		std::vector<int> values2;
		values1.reserve(n);
		values2.reserve(n);
		for (int i = 0; i < n; ++i) {
			values1.push_back(i);
			values2.push_back(i + n);
		}

		std::unordered_multiset<int> expected;
		expected.insert(values1.begin(), values1.end());
		expected.insert(values2.begin(), values2.end());

		task::lock_free_stack<int> stack;

		std::thread thread1(push_values<int>, std::ref(stack), std::ref(values1));
		std::thread thread2(push_values<int>, std::ref(stack), std::ref(values2));
		
		thread1.join();
		thread2.join();

		std::unordered_multiset<int> results;
		while (true) {
			auto result = stack.pop();
			if (!result.has_value()) {
				break;
			}
			results.insert(result.value());
		}

		assert(results == expected);
	}

	void test_mt_push_pop() {
		const size_t n = 1000;
		std::vector<int> values1;
		values1.reserve(n);
		for (int i = 0; i < n; ++i) {
			values1.push_back(i);
		}

		std::unordered_multiset<int> expected;
		expected.insert(values1.begin(), values1.end());

		task::lock_free_stack<int> stack;
		std::vector<std::optional<int>> results;

		std::thread thread1(push_values<int>, std::ref(stack), std::ref(values1));
		std::thread thread2(pop_values<int>, std::ref(stack), std::ref(results), n);

		thread1.join();
		thread2.join();

		assert(results.size() == n);
		for (auto& result : results) {
			if (!result.has_value()) {
				continue;
			}
			assert(expected.contains(result.value()));
			expected.erase(result.value());
		}
	}
}

int main() {
	tests::test_st_empty();
	tests::test_st_one_value();
	tests::test_st_many_values();
	tests::test_st_random_order();

	tests::test_mt_push();
	tests::test_mt_push_pop();
	return 0;
}
