package com.ssafy.ssafit.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QClub is a Querydsl query type for Club
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QClub extends EntityPathBase<Club> {

    private static final long serialVersionUID = 696593160L;

    public static final QClub club = new QClub("club");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath clubImg = createString("clubImg");

    public final StringPath clubName = createString("clubName");

    public final NumberPath<Integer> count = createNumber("count", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> endDate = createDateTime("endDate", java.time.LocalDateTime.class);

    public final BooleanPath finish = createBoolean("finish");

    public final StringPath fixTime = createString("fixTime");

    public final BooleanPath fri = createBoolean("fri");

    //inherited
    public final NumberPath<Integer> id = _super.id;

    public final StringPath manager = createString("manager");

    public final BooleanPath mon = createBoolean("mon");

    public final BooleanPath sat = createBoolean("sat");

    public final DateTimePath<java.time.LocalDateTime> startDate = createDateTime("startDate", java.time.LocalDateTime.class);

    public final BooleanPath sun = createBoolean("sun");

    public final BooleanPath thur = createBoolean("thur");

    public final BooleanPath tues = createBoolean("tues");

    public final BooleanPath wedn = createBoolean("wedn");

    public QClub(String variable) {
        super(Club.class, forVariable(variable));
    }

    public QClub(Path<? extends Club> path) {
        super(path.getType(), path.getMetadata());
    }

    public QClub(PathMetadata metadata) {
        super(Club.class, metadata);
    }

}

