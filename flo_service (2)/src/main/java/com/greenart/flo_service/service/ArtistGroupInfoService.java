package com.greenart.flo_service.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.greenart.flo_service.repository.ArtistGroupInfoRepository;
import com.greenart.flo_service.vo.ArtistGroupInfoInsertVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // 의존성 자동주입(autowired 자동으로 해줌)
public class ArtistGroupInfoService {
    private final ArtistGroupInfoRepository agiRepo;  // service 안에서만 사용할거기 때문에 private, repo가 변경될 가능성이 없기 때문에 final
    public Map<String, Object>addArtistGroupInfo(ArtistGroupInfoInsertVO data, MultipartFile img){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        resultMap.put("data", data);
        resultMap.put("file", img.getOriginalFilename());

        return resultMap;
    }
}
