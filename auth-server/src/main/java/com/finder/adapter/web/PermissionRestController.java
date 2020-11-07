package com.finder.adapter.web;


import com.finder.domain.Permission;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/v1/permissions")
@RequiredArgsConstructor
public class PermissionRestController {

    @GetMapping
    public List<String> load(){
        return Arrays.stream(Permission.values())
                .map(Enum::toString)
                .collect(Collectors.toList());
    }
}
