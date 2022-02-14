package com.ssafy.ssafit.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QTrainer is a Querydsl query type for Trainer
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTrainer extends EntityPathBase<Trainer> {

    private static final long serialVersionUID = 1281137955L;

    public static final QTrainer trainer = new QTrainer("trainer");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    //inherited
    public final NumberPath<Integer> id = _super.id;

    public final StringPath trainerImg = createString("trainerImg");

    public final StringPath trainerName = createString("trainerName");

    public QTrainer(String variable) {
        super(Trainer.class, forVariable(variable));
    }

    public QTrainer(Path<? extends Trainer> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTrainer(PathMetadata metadata) {
        super(Trainer.class, metadata);
    }

}

