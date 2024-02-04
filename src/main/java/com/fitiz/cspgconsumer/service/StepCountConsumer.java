package com.fitiz.cspgconsumer.service;

import com.fitiz.cspgconsumer.model.StepCountUpdateData;
import com.fitiz.cspgconsumer.repository.LeaderboardPgRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class StepCountConsumer {

    private final LeaderboardPgRepository leaderboardPgRepository;

    @KafkaListener(
            topics = {"${prop.config.broker-properties.step-count-topic}"},
            groupId = "${prop.config.broker-properties.step-count-topic-pg-consumer-group-id}",
            properties = {"spring.json.value.default.type=com.fitiz.cspgconsumer.model.StepCountUpdateData"})
    public void gameScorePgConsumer(ConsumerRecord<String, StepCountUpdateData> record) {
        var stepCountUpdateData = record.value();
        log.info("record consumed , userId: {}, score: {}", record.key(), stepCountUpdateData.steps());
        boolean updatedStepCount = leaderboardPgRepository.updateStepCount(stepCountUpdateData.userId(),
                stepCountUpdateData.steps());
        if (!updatedStepCount) {
            log.error("failed to update step count for user: {}", stepCountUpdateData.userId());
            return;
        }
        log.info("record saved to db , key: {}", record.key());
    }
}