package com.mosa.entity.statemachine;

import com.mosa.entity.model.Entity;
import com.mosa.entity.model.EntityEvents;
import com.mosa.entity.model.EntityStates;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.recipes.persist.FactoryPersistStateMachineHandler;

public class CustomFactoryPersistStateMachineHandler extends FactoryPersistStateMachineHandler<EntityStates, EntityEvents> {
    public CustomFactoryPersistStateMachineHandler(StateMachineBuilder.Builder<EntityStates, EntityEvents> builder) {
        super(builder);
    }

    public CustomFactoryPersistStateMachineHandler(StateMachineFactory<EntityStates, EntityEvents> factory) {
        super(factory);
    }

    public interface CustomPersistStateChangeListener extends GenericPersistStateChangeListener<EntityStates, EntityEvents> {
    }
}
