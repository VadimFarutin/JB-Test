# Тестовое задание к проекту "Улучшение Jacoco".

## Описание решения
Требуемая функциональность реализована в `StringUtils.findSubstring()`.  
Также реализовано два набора тестов: `StringUtilsTest` и `StringUtilsWeakTest`.  
В классе `StringUtilsWeakTest` специально не проверяются все случаи,
чтобы получить разные результаты в отчете `Jacoco`.

Сгенерировнные отчеты загружены в `reportsStrong` и `reportsWeak`.  
Как можно видеть, более слабые тесты имеют меньший процент покрытия,
в частности, они не покрывают все ветки исполнения.

## Build and Run
```
gradlew clean test --tests ru.spb.hse.farutin.StringUtilsTest.* jacocoTestReport
gradlew clean test --tests ru.spb.hse.farutin.StringUtilsWeakTest.* jacocoTestReport
```
