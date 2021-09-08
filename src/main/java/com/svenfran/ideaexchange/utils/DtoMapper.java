package com.svenfran.ideaexchange.utils;

import com.svenfran.ideaexchange.dto.IdeaDto;
import com.svenfran.ideaexchange.entity.Idea;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public final class DtoMapper {

    public static ResponseEntity<List<IdeaDto>> mapToIdeaDtoList(List<Idea> ideas) {
        List<IdeaDto> ideaDtoList = new ArrayList<>();
        for (Idea idea : ideas) {
            IdeaDto ideaDto = new IdeaDto();
            if (idea.isOpen()) {
                BeanUtils.copyProperties(idea, ideaDto);
            } else {
                BeanUtils.copyProperties(idea, ideaDto, "description");
            }
            ideaDtoList.add(ideaDto);
        }
        return ResponseEntity.ok(ideaDtoList);
    }

    public static ResponseEntity<IdeaDto> mapToIdeaDto(Idea idea) {
        IdeaDto ideaDto = new IdeaDto();
        if (idea != null) {
            if (idea.isOpen()) {
                BeanUtils.copyProperties(idea, ideaDto);
            } else {
                BeanUtils.copyProperties(idea, ideaDto, "description");
            }
            return ResponseEntity.ok(ideaDto);
        }
        return ResponseEntity.notFound().build();
    }
}
