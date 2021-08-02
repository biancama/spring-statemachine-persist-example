package com.mosa.entity.statemachine;

import com.mosa.entity.model.EntityEvents;
import com.mosa.entity.model.EntityStates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.recipes.persist.AbstractPersistStateMachineHandler;
import org.springframework.statemachine.recipes.persist.PersistStateMachineHandler;

@Configuration
public class EntityPersistHandlerConfig {

  //@Autowired
  //private StateMachine<String, String> entityStateMachine;

  @Autowired
  private StateMachineFactory<EntityStates, EntityEvents> factory;
  @Autowired
  private EntityPersistStateChangeListener entityPersistStateChangeListener;

  @Bean
  public AbstractPersistStateMachineHandler persistStateMachineHandler() {
    CustomFactoryPersistStateMachineHandler customFactoryPersistStateMachineHandler = new CustomFactoryPersistStateMachineHandler(factory);
    customFactoryPersistStateMachineHandler.addPersistStateChangeListener(entityPersistStateChangeListener);
    /*
    PersistStateMachineHandler handler = new PersistStateMachineHandler(entityStateMachine);
    handler.addPersistStateChangeListener(entityPersistStateChangeListener);
    return handler;

     */
    return customFactoryPersistStateMachineHandler;
  }
}
