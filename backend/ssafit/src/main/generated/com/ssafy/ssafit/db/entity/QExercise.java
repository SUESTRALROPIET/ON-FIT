package com.ssafy.ssafit.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QExercise is a Querydsl query type for Exercise
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QExercise extends EntityPathBase<Exercise> {

    private static final long serialVersionUID = 1840779050L;

    public static final QExercise exercise = new QExercise("exercise");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final NumberPath<Integer> calorie = createNumber("calorie", Integer.class);

    public final NumberPath<Integer> count = createNumber("count", Integer.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath kind = createString("kind");

    public QExercise(String variable) {
        super(Exercise.class, forVariable(variable));
    }

    public QExercise(Path<? extends Exercise> path) {
        super(path.getType(), path.getMetadata());
    }

    public QExercise(PathMetadata metadata) {
        super(Exercise.class, metadata);
    }

}

