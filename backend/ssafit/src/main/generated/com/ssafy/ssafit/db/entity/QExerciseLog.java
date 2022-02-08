package com.ssafy.ssafit.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QExerciseLog is a Querydsl query type for ExerciseLog
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QExerciseLog extends EntityPathBase<ExerciseLog> {

    private static final long serialVersionUID = 506319802L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QExerciseLog exerciseLog = new QExerciseLog("exerciseLog");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final NumberPath<Integer> exCount = createNumber("exCount", Integer.class);

    public final QExercise exId;

    public final NumberPath<Integer> exTime = createNumber("exTime", Integer.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final QUser userId;

    public QExerciseLog(String variable) {
        this(ExerciseLog.class, forVariable(variable), INITS);
    }

    public QExerciseLog(Path<? extends ExerciseLog> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QExerciseLog(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QExerciseLog(PathMetadata metadata, PathInits inits) {
        this(ExerciseLog.class, metadata, inits);
    }

    public QExerciseLog(Class<? extends ExerciseLog> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.exId = inits.isInitialized("exId") ? new QExercise(forProperty("exId")) : null;
        this.userId = inits.isInitialized("userId") ? new QUser(forProperty("userId"), inits.get("userId")) : null;
    }

}

