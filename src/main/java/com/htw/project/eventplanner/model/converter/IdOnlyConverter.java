package com.htw.project.eventplanner.model.converter;

import com.fasterxml.jackson.databind.util.StdConverter;
import com.htw.project.eventplanner.model.Identifiable;

import java.util.HashMap;
import java.util.Map;

public class IdOnlyConverter extends StdConverter<Identifiable, Map> {

    @Override
    public Map convert(Identifiable identifiable) {
        if (identifiable == null) {
            return null;
        }

        Map<String, Long> map = new HashMap<>();
        map.put("id", identifiable.getId());

        return map;
    }

}
