//package com.yupi.hewoj.syntask;
//
//import com.yupi.hewoj.model.entity.AnswerThumb;
//import com.yupi.hewoj.model.entity.QuestionAnswer;
//import com.yupi.hewoj.service.AnswerThumbService;
//import com.yupi.hewoj.service.QuestionAnswerService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.util.*;
//import java.util.stream.Collectors;
//
//@Component
//@Slf4j
//public class AnswerLikesSynTask
//{
//    @Autowired
//    private QuestionAnswerService questionAnswerService;
//    @Autowired
//    private AnswerThumbService answerThumbService;
//
//    /**
//     * 定时同步文章点赞信息
//     */
//    @Scheduled(cron = "0 0 12 */1 * *") // 每1天
////    @Scheduled(cron = "0 */1 * * * *") // 每一分钟执行一次
//    public void synArticleLikes()
//    {
//        log.info("定时同步文章点赞信息 - " + new Date());
//        //查询出所有题解
//        List<QuestionAnswer> answerList = questionAnswerService.list();
//        // 获取所有题解点赞记录
//        List<AnswerThumb> answerThumbList = answerThumbService.list();
//        // 按题解id分组，Map的值为List<AnswerThumb>
//        Map<Long, List<AnswerThumb>> idToArticleLikesMap = answerThumbList.stream().collect(Collectors.groupingBy(AnswerThumb::getAnswerId));
//        // 定义要更新的题解
//        List<QuestionAnswer> toUpdateSolution = new ArrayList<>();
//        // 定义最终要删除和添加的点赞记录
//        List<AnswerThumb> toDeleteArticleLikes = new ArrayList<>();
//        List<AnswerThumb> toAddArticleLikes = new ArrayList<>();
//        for (QuestionAnswer answer : answerList)
//        {
//            // 从redis中获取题解id对应的点赞数
//            Long answerThumbFromRedis = answerThumbService.getArticleLikes(answer.getId());
//            // 从redis中题解id对应的具体点赞用户集合
//            List<Long> answerThumbUserIds = answerThumbService.getArticleLikedUsers(answer.getId()).stream().map(Object::toString) // 假设返回的元素是字符串类型，如果不是，可以根据实际情况调整
//                    .map(Long::parseLong).collect(Collectors.toList());
//            // 从数据库中获取遍历到的题解的点赞记录
//            List<AnswerThumb> articleLikesFromDB = idToArticleLikesMap.get(answer.getId());
//            if (articleLikesFromDB == null)
//            {
//                articleLikesFromDB = new ArrayList<>();
//            }
//            /*如果redis的点赞用户集合为空，则不执行删除和添加，
//                这种情况我们认为redis宕机然后刚刚重启
//                并将数据库中的对应数据同步至redis中
//             */
//
//            //如果该题解在redis中还没有点赞相关的记录，就将数据库中国查询到的该题解的点赞数据上传到redis中
//            if (answerThumbUserIds.isEmpty())
//            {
//                for (AnswerThumb answerThumb : articleLikesFromDB)
//                {
//                    answerThumbService.addUserToLikeSet(answer.getId(), answerThumb.getUserId());
//                }
//                answerThumbService.setArticleLikes(answer.getId(), articleLikesFromDB.size());
//                continue;
//            }
//            // 比较数目和结合的size，使其一致，以集合size为准，并更新article对应记录的点赞数
//            long articleLikeListSize = Long.parseLong(answerThumbFromRedis.toString());
//            if (answerThumbFromRedis.equals(articleLikeListSize))
//            {
//                answerThumbService.setArticleLikes(answer.getId(), articleLikeListSize);
//            }
//            if (!answer.getLikeCnt().equals(articleLikeListSize))
//            {
//                answer.setLikeCnt((int)articleLikeListSize);
//                toUpdateSolution.add(answer);
//            }
//            Iterator<AnswerThumb> iterator = articleLikesFromDB.iterator();
//            while (iterator.hasNext())
//            {
//                AnswerThumb likes = iterator.next();
//                if (!answerLikedUserIds.contains(likes.getUserId()))
//                {
//                    toDeleteArticleLikes.add(likes);
//                }
//            }
//            // 获得要添加的文章点赞记录
//            List<Long> collectUserIdFromDB = articleLikesFromDB.stream().map(AnswerThumb::getUserId).collect(Collectors.toList());
//            for (Long answerLikedUserId : answerLikedUserIds)
//            {
//                if (!collectUserIdFromDB.contains(answerLikedUserId))
//                {
//                    AnswerThumb answerThumb = new AnswerThumb();
//                    answerThumb.setAnswerId(answer.getId());
//                    answerThumb.setUserId(answerLikedUserId);
//                    toAddArticleLikes.add(answerThumb);
//                }
//            }
//        }
//        // 更新question_solution表
//        if (toUpdateSolution.size() > 0)
//        {
//            questionSolutionService.updateBatchById(toUpdateSolution);
//        }
//        // 更新article_likes表中的字段（删除和添加）
//        if (toDeleteArticleLikes.size() > 0)
//        {
//            articleLikesService.removeByIds(toDeleteArticleLikes.stream().map(ArticleLikes::getId).collect(Collectors.toList()));
//        }
//        if (toAddArticleLikes.size() > 0)
//        {
//            articleLikesService.saveBatch(toAddArticleLikes);
//        }
//    }
//}