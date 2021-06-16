package com.jvo.biglossalertsmonitoring.service;

import com.jvo.biglossalertsmonitoring.domain.BlockingEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

@Slf4j
@Service
public class BlockingEventsPublisher {

    private final KafkaTemplate<String, BlockingEvent> blockingEventsKafkaTemplate;

    private final String blockingEventsTopic = "blocking-events";

    public BlockingEventsPublisher(KafkaTemplate<String, BlockingEvent> blockingEventsKafkaTemplate) {
        this.blockingEventsKafkaTemplate = blockingEventsKafkaTemplate;
    }

    public ListenableFuture<SendResult<String, BlockingEvent>> sendBlockingEvents(BlockingEvent blockingEvent) {
        log.trace("Attempt to send message {}", blockingEvent);
        return blockingEventsKafkaTemplate.send(blockingEventsTopic, blockingEvent.getPartitionKey(), blockingEvent);
    }

}
