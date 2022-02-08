package com.ssafy.ssafit.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 697135645L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUser user = new QUser("user");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final ListPath<ExerciseLog, QExerciseLog> exLogList = this.<ExerciseLog, QExerciseLog>createList("exLogList", ExerciseLog.class, QExerciseLog.class, PathInits.DIRECT2);

    public final NumberPath<Integer> foodLog = createNumber("foodLog", Integer.class);

    public final StringPath fullName = createString("fullName");

    public final QHealthLog healthLogId;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final QTrainer trainerId;

    public final StringPath userEmail = createString("userEmail");

    public QUser(String variable) {
        this(User.class, forVariable(variable), INITS);
    }

    public QUser(Path<? extends User> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUser(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUser(PathMetadata metadata, PathInits inits) {
        this(User.class, metadata, inits);
    }

    public QUser(Class<? extends User> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.healthLogId = inits.isInitialized("healthLogId") ? new QHealthLog(forProperty("healthLogId")) : null;
        this.trainerId = inits.isInitialized("trainerId") ? new QTrainer(forProperty("trainerId")) : null;
    }

}

