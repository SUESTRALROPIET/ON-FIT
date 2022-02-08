package com.ssafy.ssafit.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QHealthLog is a Querydsl query type for HealthLog
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QHealthLog extends EntityPathBase<HealthLog> {

    private static final long serialVersionUID = 1937332278L;

    public static final QHealthLog healthLog = new QHealthLog("healthLog");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Integer> monthlyCal = createNumber("monthlyCal", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> monthlyTime = createDateTime("monthlyTime", java.time.LocalDateTime.class);

    public QHealthLog(String variable) {
        super(HealthLog.class, forVariable(variable));
    }

    public QHealthLog(Path<? extends HealthLog> path) {
        super(path.getType(), path.getMetadata());
    }

    public QHealthLog(PathMetadata metadata) {
        super(HealthLog.class, metadata);
    }

}

