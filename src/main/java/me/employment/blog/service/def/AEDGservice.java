package me.employment.blog.service.def;

import java.util.List;
import java.util.Map;

// add edit delete get
public interface AEDGservice<Id,Dto> {
    Map<String,String> add(Dto dto);
    Map<String,String> edit(Id id,Dto dto);
    Map<String,String> delete(Id id);
    List<Dto> get();
    List<Dto> get(Id id);




}
