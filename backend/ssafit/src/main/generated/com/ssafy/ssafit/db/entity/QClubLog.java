package com.ssafy.ssafit.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QClubLog is a Querydsl query type for ClubLog
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QClubLog extends EntityPathBase<ClubLog> {

    private static final long serialVersionUID = -1075068132L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QClubLog clubLog = new QClubLog("clubLog");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final QClub clubId;

    public final NumberPath<Integer> exCount = createNumber("exCount", Integer.class);

    public final QExercise exerciseId;

    public final NumberPath<Double> exTime = createNumber("exTime", Double.class);

    //inherited
    public final NumberPath<Integer> id = _super.id;

    public QClubLog(String variable) {
        this(ClubLog.class, forVariable(variable), INITS);
    }

    public QClubLog(Path<? extends ClubLog> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QClubLog(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QClubLog(PathMetadata metadata, PathInits inits) {
        this(ClubLog.class, metadata, inits);
    }

    public QClubLog(Class<? extends ClubLog> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.clubId = inits.isInitialized("clubId") ? new QClub(forProperty("clubId")) : null;
        this.exerciseId = inits.isInitialized("exerciseId") ? new QExercise(forProperty("exerciseId")) : null;
    }

}

