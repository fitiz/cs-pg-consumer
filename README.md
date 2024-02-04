### Challenge service PostgreSQL Kafka consumer

- Kafka consumer for storing user step counts in PostgreSQL

```java
@KafkaListener(
            topics = {"${prop.config.broker-properties.step-count-topic}"},
            groupId = "${prop.config.broker-properties.step-count-topic-pg-consumer-group-id}",
            properties = {"spring.json.value.default.type=com.fitiz.cspgconsumer.model.StepCountUpdateData"})
    public void stepCountPgConsumer(ConsumerRecord<String, StepCountUpdateData> record) {
        var stepCountUpdateData = record.value();
        log.info("Step count consumed, user: {}, step count: {}", stepCountUpdateData.username(), stepCountUpdateData.steps());
        boolean updatedStepCount = leaderboardPgRepository.updateStepCount(stepCountUpdateData.userId(),
                stepCountUpdateData.steps());
        if (!updatedStepCount) {
            log.error("Failed to update step count for user: {}", stepCountUpdateData.userId());
            return;
        }
        log.info("Step count saved to DB , user: {}", stepCountUpdateData.userId());
    }
```
