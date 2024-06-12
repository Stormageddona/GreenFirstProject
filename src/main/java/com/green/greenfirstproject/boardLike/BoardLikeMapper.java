package com.green.greenfirstproject.boardLike;

import com.green.greenfirstproject.boardLike.model.FeedFavoriteEntity;
import com.green.greenfirstproject.boardLike.model.FeedLike;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardLikeMapper {
    int postFeedLike (FeedLike p);

    List<FeedFavoriteEntity> postCommunityFavorite(FeedLike p);

    int delFeedLike(FeedLike p);
}
