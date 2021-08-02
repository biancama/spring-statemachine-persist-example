package com.mosa.entity.statemachine;

import com.mosa.entity.model.EntityEvents;
import com.mosa.entity.model.EntityStates;
import com.mosa.entity.statemachine.actions.IdleToActiveAction;
import com.mosa.entity.statemachine.guards.IdleToActiveGuard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableStateMachineFactory
        (name = "entityStateMachine")
public class EntityStateMachineConfig extends EnumStateMachineConfigurerAdapter<EntityStates, EntityEvents> {

  @Autowired
  private IdleToActiveGuard idleToActiveGuard;

  @Autowired
  private IdleToActiveAction idleToActiveAction;

  @Override
  public void configure(StateMachineStateConfigurer<EntityStates, EntityEvents> states)
          throws Exception {

    //Set<String> stringStates = new HashSet<>();
    // EnumSet.allOf(EntityStates.class).forEach(entity -> stringStates.add(entity));
    states.withStates()
            .initial(EntityStates.IDLE)
            .end(EntityStates.DELETED)
            .states(EnumSet.allOf(EntityStates.class));
  }

  @Override
  public void configure(StateMachineTransitionConfigurer<EntityStates, EntityEvents> transitions)
          throws Exception {
    transitions.withExternal()
            .source(EntityStates.IDLE).target(EntityStates.ACTIVE)
            .event(EntityEvents.ACTIVATE)
            .guard(idleToActiveGuard).action(idleToActiveAction)
            .and().withExternal()
            .source(EntityStates.ACTIVE).target(EntityStates.IDLE)
            .event(EntityEvents.IDLE)
            .and().withExternal()
            .source(EntityStates.IDLE).target(EntityStates.DELETED)
            .event(EntityEvents.DELETE)
            .and().withExternal()
            .source(EntityStates.ACTIVE).target(EntityStates.DELETED)
            .event(EntityEvents.DELETE);
  }
  /*
  @Override
  public void configure(StateMachineStateConfigurer<String, String> states)
      throws Exception {
    Set<String> stringStates = new HashSet<>();
    EnumSet.allOf(EntityStates.class).forEach(entity -> stringStates.add(entity));
    states.withStates()
        .initial(EntityStates.IDLE)
        .end(EntityStates.DELETED)
        .states(stringStates);
  }

  @Override
  public void configure(StateMachineTransitionConfigurer<String, String> transitions)
      throws Exception {
    transitions.withExternal()
        .source(EntityStates.IDLE).target(EntityStates.ACTIVE)
        .event(EntityEvents.ACTIVATE)
        .guard(idleToActiveGuard).action(idleToActiveAction)
        .and().withExternal()
        .source(EntityStates.ACTIVE).target(EntityStates.IDLE)
        .event(EntityEvents.IDLE)
        .and().withExternal()
        .source(EntityStates.IDLE).target(EntityStates.DELETED)
        .event(EntityEvents.DELETE)
        .and().withExternal()
        .source(EntityStates.ACTIVE).target(EntityStates.DELETED)
        .event(EntityEvents.DELETE);
  }

   */
}
