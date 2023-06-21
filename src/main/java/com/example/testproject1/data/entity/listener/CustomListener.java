package com.example.testproject1.data.entity.listener;

import com.example.testproject1.data.entity.ListenerEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;

public class CustomListener {

    private final Logger LOGGER = LoggerFactory.getLogger(CustomListener.class);

    @PostLoad
    public void postLoad(ListenerEntity entity) {
        LOGGER.info("[postLoad] called!!");
    }

    @PrePersist
    public void prePersist(ListenerEntity entity) {
        LOGGER.info("[prePersist] called!!");
    }

    @PostPersist
    public void postPersist(ListenerEntity entity) {
        LOGGER.info("[postPersist] called!!");
    }

    @PreUpdate
    public void preUpdate(ListenerEntity entity) {
        LOGGER.info("[preUpdate] called!!");
    }

    @PostUpdate
    public void postUpdate(ListenerEntity entity) {
        LOGGER.info("[postUpdate] called!!");
    }

    @PreRemove
    public void preRemove(ListenerEntity entity) {
        LOGGER.info("[preRemove] called!!");
    }

    @PostRemove
    public void postRemove(ListenerEntity entity) {
        LOGGER.info("[postRemove] called!!");
    }
}
