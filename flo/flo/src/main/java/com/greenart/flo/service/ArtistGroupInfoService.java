package com.greenart.flo.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.greenart.flo.entity.ArtistGroupInfoEntity;
import com.greenart.flo.repository.ArtistGroupInfoRepository;
import com.greenart.flo.repository.CompanyRepository;
import com.greenart.flo.vo.ArtistGroupInfoInsertVO;
import com.greenart.flo.vo.ArtistGroupResponseVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // 의존성 자동 주입 - @Autowired 자동으로 해줌
public class ArtistGroupInfoService {
    private final FileService fileService;
    private final ArtistGroupInfoRepository agiRepo;
    private final CompanyRepository companyRepository;
    
    public Map<String, Object> addArtistGroupInfo(ArtistGroupInfoInsertVO data, MultipartFile img) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        String savedFilePath = "";

        try {
            savedFilePath = fileService.saveImageFile("artist_group", img);
        }
        catch(Exception e) {
            System.out.println("파일 전송 실패");
            resultMap.put("stats", false);
            resultMap.put("message", "파일 전송에 실패했습니다.");
            resultMap.put("code", HttpStatus.INTERNAL_SERVER_ERROR);
            return resultMap;
        }

        ArtistGroupInfoEntity entity = ArtistGroupInfoEntity.builder()
                .agiName(data.getName())
                .agiDebutYear(data.getDebutYear())
                // .agiPciSeq(data.getCompany())
                .company(companyRepository.findById(data.getCompany()).get())
                .agiImg(savedFilePath)
                .build();
        agiRepo.save(entity);

        resultMap.put("status", true);
        resultMap.put("message", "아티스트 그룹이 추가되었습니다.");
        resultMap.put("code", HttpStatus.OK);
        return resultMap;
    }

    public ArtistGroupResponseVO getArtistGroupList(String keyword, Pageable pageable) {
        if (keyword==null) keyword = "";
        Page<ArtistGroupInfoEntity> agiPage = agiRepo.findByAgiNameContains(keyword, pageable);
        ArtistGroupResponseVO response = ArtistGroupResponseVO.builder()
                .list(agiPage.getContent())
                .total(agiPage.getTotalElements())
                .totalPage(agiPage.getTotalPages())
                .currentPage(agiPage.getNumber())
                .build();
        return response;
    }
}
