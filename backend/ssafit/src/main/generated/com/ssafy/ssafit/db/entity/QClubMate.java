package com.ssafy.ssafit.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QClubMate is a Querydsl query type for ClubMate
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QClubMate extends EntityPathBase<ClubMate> {

    private static final long serialVersionUID = 1032643117L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QClubMate clubMate = new QClubMate("clubMate");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final QClub clubId;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final QUser userId;

    public QClubMate(String variable) {
        this(ClubMate.class, forVariable(variable), INITS);
    }

    public QClubMate(Path<? extends ClubMate> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QClubMate(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QClubMate(PathMetadata metadata, PathInits inits) {
        this(ClubMate.class, metadata, inits);
    }

    public QClubMate(Class<? extends ClubMate> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.clubId = inits.isInitialized("clubId") ? new QClub(forProperty("clubId")) : null;
        this.userId = inits.isInitialized("userId") ? new QUser(forProperty("userId"), inits.get("userId")) : null;
    }

}

