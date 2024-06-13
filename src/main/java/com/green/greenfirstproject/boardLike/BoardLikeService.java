package com.green.greenfirstproject.boardLike;


import com.green.greenfirstproject.boardLike.model.FeedFavoriteEntity;
import com.green.greenfirstproject.boardLike.model.FeedLike;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardLikeService {
    private final BoardLikeMapper mapper;


    public int  postCommunityFavorite(FeedLike p){
        int res =mapper.delFeedLike(p);
        if(res == 1){
            return 0;

        }
        return  mapper.postFeedLike(p);
    }
}
// 어쩔티비
// 저쩔티비
