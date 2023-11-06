package me.employment.blog.mapper.def;

public interface Mapper<Dto,Entity> {
        Dto dto(Entity entity);
        Entity entity(Dto dto);
}
