/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80036
 Source Host           : localhost:3306
 Source Schema         : hewoj

 Target Server Type    : MySQL
 Target Server Version : 80036
 File Encoding         : 65001

 Date: 02/09/2024 16:34:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for answer_favour
-- ----------------------------
DROP TABLE IF EXISTS `answer_favour`;
CREATE TABLE `answer_favour`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `answerId` bigint NOT NULL COMMENT '题解 id',
  `userId` bigint NOT NULL COMMENT '创建用户 id',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_postId`(`answerId` ASC) USING BTREE,
  INDEX `idx_userId`(`userId` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '题解收藏' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of answer_favour
-- ----------------------------
INSERT INTO `answer_favour` VALUES (1, 1783401980166672386, 1797639081053741057, '2024-06-06 17:03:34', '2024-06-06 17:06:10');
INSERT INTO `answer_favour` VALUES (2, 1783402046419988482, 1797639081053741057, '2024-06-06 17:04:53', '2024-06-06 17:06:10');
INSERT INTO `answer_favour` VALUES (3, 1783417053727752194, 1797639081053741057, '2024-06-06 17:04:53', '2024-06-06 17:04:53');

-- ----------------------------
-- Table structure for answer_thumb
-- ----------------------------
DROP TABLE IF EXISTS `answer_thumb`;
CREATE TABLE `answer_thumb`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `answerId` bigint NOT NULL COMMENT '题解 id',
  `userId` bigint NOT NULL COMMENT '点赞用户 id',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `isDelete` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_postId`(`answerId` ASC) USING BTREE,
  INDEX `idx_userId`(`userId` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '题解点赞' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of answer_thumb
-- ----------------------------

-- ----------------------------
-- Table structure for comment_answer
-- ----------------------------
DROP TABLE IF EXISTS `comment_answer`;
CREATE TABLE `comment_answer`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `userId` bigint NOT NULL COMMENT '用户id',
  `answerId` bigint NOT NULL COMMENT '题解id',
  `commentContent` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '评论内容',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `isDelete` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '题解评论表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of comment_answer
-- ----------------------------
INSERT INTO `comment_answer` VALUES (1, 1744901815402741761, 1, 'test', '2024-04-28 11:03:10', '2024-04-28 11:57:12', 0);
INSERT INTO `comment_answer` VALUES (2, 1744901815402741761, 1, 'test', '2024-04-29 17:36:55', '2024-04-29 17:36:56', 0);
INSERT INTO `comment_answer` VALUES (3, 1744901815402741761, 1783398992136216577, '1', '2024-05-07 09:57:24', '2024-05-07 12:08:09', 0);
INSERT INTO `comment_answer` VALUES (4, 1744901815402741761, 1783398992136216577, '2', '2024-05-07 09:57:33', '2024-05-07 12:08:09', 0);
INSERT INTO `comment_answer` VALUES (5, 1744901815402741761, 1783398992136216577, '3', '2024-05-07 09:57:33', '2024-05-07 12:08:09', 0);
INSERT INTO `comment_answer` VALUES (6, 1744901815402741761, 1783398992136216577, '4', '2024-05-07 09:57:33', '2024-05-07 12:08:09', 0);
INSERT INTO `comment_answer` VALUES (7, 1744901815402741761, 1783398992136216577, '5', '2024-05-07 09:57:33', '2024-05-07 12:08:09', 0);
INSERT INTO `comment_answer` VALUES (8, 0, 1783398992136216577, 'asas', '2024-05-08 11:20:27', '2024-05-08 11:20:27', 0);
INSERT INTO `comment_answer` VALUES (9, 0, 1783398992136216577, 'sasa', '2024-05-08 11:20:59', '2024-05-08 11:20:59', 0);
INSERT INTO `comment_answer` VALUES (10, 0, 1783398992136216577, 'asas', '2024-05-08 11:23:34', '2024-05-08 11:23:34', 0);
INSERT INTO `comment_answer` VALUES (11, 0, 1783398992136216577, 'sasa', '2024-05-08 11:25:19', '2024-05-08 11:25:19', 0);
INSERT INTO `comment_answer` VALUES (12, 1744901815402741761, 1783398992136216577, 'ddddddd', '2024-05-08 11:25:36', '2024-05-08 11:25:36', 0);
INSERT INTO `comment_answer` VALUES (13, 1744901815402741761, 1783398992136216577, 'dddddddss', '2024-05-08 11:26:26', '2024-05-08 11:26:26', 0);
INSERT INTO `comment_answer` VALUES (14, 1744901815402741761, 1783398992136216577, 'dddddddsssasa', '2024-05-08 11:26:33', '2024-05-08 11:26:33', 0);
INSERT INTO `comment_answer` VALUES (15, 1744901815402741761, 1783398992136216577, 'sasa', '2024-05-08 11:35:23', '2024-05-08 11:35:23', 0);
INSERT INTO `comment_answer` VALUES (16, 1744901815402741761, 1783398992136216577, '## sas\n```java\nmian\n```\n### sasa', '2024-05-08 11:35:50', '2024-05-08 11:35:50', 0);
INSERT INTO `comment_answer` VALUES (17, 1779768810514046978, 1783398992136216577, 'reply', '2024-05-08 14:21:25', '2024-05-08 14:22:26', 0);
INSERT INTO `comment_answer` VALUES (18, 1779768810514046978, 1783398992136216577, 'reply', '2024-05-08 14:22:18', '2024-05-08 14:22:26', 0);
INSERT INTO `comment_answer` VALUES (19, 1744901815402741761, 1783398992136216577, 'dssds', '2024-05-08 14:55:00', '2024-05-08 14:55:00', 0);
INSERT INTO `comment_answer` VALUES (20, 1744901815402741761, 1783398992136216577, 'dfgdfgdfgdf', '2024-05-08 15:04:03', '2024-05-08 15:04:03', 0);
INSERT INTO `comment_answer` VALUES (21, 1744901815402741761, 1783398992136216577, '# sasa\n```java\nmain\n\n', '2024-05-08 15:04:27', '2024-05-08 15:04:27', 0);
INSERT INTO `comment_answer` VALUES (22, 1744901815402741761, 1783398992136216577, 'tstst', '2024-06-03 16:36:55', '2024-06-03 16:36:55', 0);
INSERT INTO `comment_answer` VALUES (23, 1800457759336017922, 1783319299596853250, 'test', '2024-07-29 11:04:49', '2024-07-29 11:04:49', 0);

-- ----------------------------
-- Table structure for comment_post
-- ----------------------------
DROP TABLE IF EXISTS `comment_post`;
CREATE TABLE `comment_post`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `userId` bigint NOT NULL COMMENT '用户id',
  `postId` bigint NOT NULL COMMENT '贴子id',
  `commentContent` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '评论内容',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `isDelete` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '贴子主评论表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of comment_post
-- ----------------------------
INSERT INTO `comment_post` VALUES (1, 1797639081053741057, 19, 'comment post', '2024-06-07 03:54:25', '2024-06-07 03:54:25', 0);
INSERT INTO `comment_post` VALUES (2, 1797639081053741057, 19, 'comment post', '2024-06-07 03:54:40', '2024-06-07 03:54:40', 0);
INSERT INTO `comment_post` VALUES (3, 1797639081053741057, 19, 'test comment post', '2024-06-07 07:02:03', '2024-06-07 11:45:44', 0);
INSERT INTO `comment_post` VALUES (4, 1797639081053741057, 19, '', '2024-06-07 07:11:39', '2024-06-07 07:11:39', 0);
INSERT INTO `comment_post` VALUES (5, 1797639081053741057, 19, 'sdsdsds', '2024-06-07 07:23:15', '2024-06-07 07:23:15', 0);
INSERT INTO `comment_post` VALUES (6, 1797639081053741057, 20, '添加主评论', '2024-06-07 11:38:10', '2024-06-07 19:21:10', 0);
INSERT INTO `comment_post` VALUES (7, 1797639081053741057, 19, '', '2024-06-07 18:26:24', '2024-06-07 18:26:24', 0);
INSERT INTO `comment_post` VALUES (8, 1797639081053741057, 19, 'sas', '2024-06-07 19:25:42', '2024-06-07 19:25:42', 0);
INSERT INTO `comment_post` VALUES (9, 1797639081053741057, 21, '', '2024-06-10 17:25:47', '2024-06-10 17:25:47', 0);
INSERT INTO `comment_post` VALUES (10, 1797639081053741057, 21, 'test', '2024-06-27 12:35:22', '2024-06-27 12:35:22', 0);

-- ----------------------------
-- Table structure for comment_post_reply
-- ----------------------------
DROP TABLE IF EXISTS `comment_post_reply`;
CREATE TABLE `comment_post_reply`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `userId` bigint NULL DEFAULT NULL COMMENT '回复人的id',
  `replyContent` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '回复内容',
  `targetCommentId` bigint NOT NULL COMMENT '被回复的评论Id',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `isDelete` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '对贴子评论的回复表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of comment_post_reply
-- ----------------------------
INSERT INTO `comment_post_reply` VALUES (1, 1797639081053741057, 'reply post comment', 1, '2024-06-07 05:18:04', '2024-06-07 05:42:02', 0);
INSERT INTO `comment_post_reply` VALUES (2, 1797639081053741057, 'comment', 1, '2024-06-07 05:18:04', '2024-06-07 05:42:02', 0);
INSERT INTO `comment_post_reply` VALUES (3, 1797639081053741057, 'reply comment post', 1, '2024-06-07 06:09:07', '2024-06-07 06:09:07', 0);
INSERT INTO `comment_post_reply` VALUES (4, 1797639081053741057, 'asas', 1, '2024-06-07 06:13:47', '2024-06-07 06:13:47', 0);
INSERT INTO `comment_post_reply` VALUES (5, 1797639081053741057, '', 1, '2024-06-07 06:17:35', '2024-06-07 06:17:35', 0);
INSERT INTO `comment_post_reply` VALUES (6, 1797639081053741057, '', 1, '2024-06-07 06:18:16', '2024-06-07 06:18:16', 0);
INSERT INTO `comment_post_reply` VALUES (7, 1797639081053741057, '', 1, '2024-06-07 06:19:24', '2024-06-07 06:19:24', 0);
INSERT INTO `comment_post_reply` VALUES (8, 1797639081053741057, '@何苇 : asasasa', 1, '2024-06-07 06:28:34', '2024-06-07 06:28:34', 0);
INSERT INTO `comment_post_reply` VALUES (9, 1797639081053741057, '@何苇 : hhhh', 6, '2024-06-07 06:38:05', '2024-06-10 17:18:25', 0);
INSERT INTO `comment_post_reply` VALUES (10, 1797639081053741057, '@何苇 : sasa', 6, '2024-06-10 17:26:45', '2024-06-10 17:26:45', 0);
INSERT INTO `comment_post_reply` VALUES (11, 1797639081053741057, '@何苇 : fdfd', 10, '2024-06-27 12:35:45', '2024-06-27 12:35:45', 0);

-- ----------------------------
-- Table structure for comment_reply
-- ----------------------------
DROP TABLE IF EXISTS `comment_reply`;
CREATE TABLE `comment_reply`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `userId` bigint NULL DEFAULT NULL COMMENT '回复人的id',
  `replyContent` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '回复内容',
  `targetCommentId` bigint NOT NULL COMMENT '被回复的评论Id',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `isDelete` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除',
  `targetUserId` bigint NULL DEFAULT NULL COMMENT '回复的对象的id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2121212136 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '对评论的回复表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of comment_reply
-- ----------------------------
INSERT INTO `comment_reply` VALUES (2121212121, 1744901815402741761, 'reply3', 3, '2024-05-08 16:47:45', '2024-05-08 16:47:45', 0, NULL);
INSERT INTO `comment_reply` VALUES (2121212122, 1744901815402741761, 'reply3', 3, '2024-05-08 16:47:57', '2024-05-08 16:47:57', 0, NULL);
INSERT INTO `comment_reply` VALUES (2121212123, 1744901815402741761, 'reply4', 4, '2024-05-10 14:25:38', '2024-05-10 14:25:38', 0, NULL);
INSERT INTO `comment_reply` VALUES (2121212124, 1744901815402741761, 'reply4', 4, '2024-05-10 14:25:38', '2024-05-10 14:25:38', 0, NULL);
INSERT INTO `comment_reply` VALUES (2121212125, 1744901815402741761, 'testreply', 10, '2024-05-29 10:23:09', '2024-05-29 10:23:09', 0, NULL);
INSERT INTO `comment_reply` VALUES (2121212131, 1744901815402741761, '@hew : esas', 3, '2024-06-03 16:32:26', '2024-06-03 16:32:26', 0, 1744901815402741761);
INSERT INTO `comment_reply` VALUES (2121212132, 1744901815402741761, '@hew : jkkj', 3, '2024-06-03 16:38:03', '2024-06-03 16:38:03', 0, 1744901815402741761);
INSERT INTO `comment_reply` VALUES (2121212133, 1797548379724599297, '@yuoi : dsds', 3, '2024-06-03 16:40:42', '2024-06-03 16:40:42', 0, 1744901815402741761);
INSERT INTO `comment_reply` VALUES (2121212134, 1797639081053741057, '@hew : gggg', 3, '2024-06-07 06:29:39', '2024-06-07 06:29:39', 0, 1797548379724599297);
INSERT INTO `comment_reply` VALUES (2121212135, 1797639081053741057, '@yuoi : 哈哈哈哈哈哈哈哈哈哈', 3, '2024-06-07 12:34:32', '2024-06-07 12:34:32', 0, 1744901815402741761);
INSERT INTO `comment_reply` VALUES (2121212136, 1800457759336017922, '@管理员 : test', 23, '2024-07-29 11:04:54', '2024-07-29 11:04:54', 0, 1800457759336017922);

-- ----------------------------
-- Table structure for post
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `images` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '帖子对应的图片',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '内容',
  `thumbNum` int NOT NULL DEFAULT 0 COMMENT '点赞数',
  `favourNum` int NOT NULL DEFAULT 0 COMMENT '收藏数',
  `userId` bigint NOT NULL COMMENT '创建用户 id',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `isDelete` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_userId`(`userId` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '帖子' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of post
-- ----------------------------
INSERT INTO `post` VALUES (19, '[\"http://192.168.68.132:9000/hewoj-minio/1716526811356.png\"]', 'testpost', 0, 0, 1744901815402741761, '2024-05-24 13:00:19', '2024-05-24 13:00:19', 0);
INSERT INTO `post` VALUES (20, '[\"http://192.168.68.132:9000/hewoj-minio/1716534453182.png\",\"http://192.168.68.132:9000/hewoj-minio/1716534456444.png\"]', '', 0, 0, 1744901815402741761, '2024-05-24 15:07:37', '2024-05-24 15:07:37', 0);
INSERT INTO `post` VALUES (21, '[\"http://192.168.68.132:9000/hewoj-minio/1716534474499.png\",\"http://192.168.68.132:9000/hewoj-minio/1716534477318.png\",\"http://192.168.68.132:9000/hewoj-minio/1716534481135.png\",\"http://192.168.68.132:9000/hewoj-minio/1716534484959.png\"]', 'asjak=的黄金时代回家', 0, 0, 1744901815402741761, '2024-05-24 15:08:13', '2024-05-24 15:08:13', 0);
INSERT INTO `post` VALUES (22, '[\"http://192.168.68.132:9000/hewoj-minio/1716534541995.png\",\"http://192.168.68.132:9000/hewoj-minio/1716534547097.png\",\"http://192.168.68.132:9000/hewoj-minio/1716534550135.png\",\"http://192.168.68.132:9000/hewoj-minio/1716534553283.png\",\"http://192.168.68.132:9000/hewoj-minio/1716534555928.png\",\"http://192.168.68.132:9000/hewoj-minio/1716534560827.png\"]', 'D', 0, 0, 1744901815402741761, '2024-05-24 15:09:23', '2024-05-24 15:09:23', 0);
INSERT INTO `post` VALUES (23, '[\"http://192.168.68.132:9000/hewoj-minio/1716538190623.png\"]', 'sasas', 0, 0, 1744901815402741761, '2024-05-24 16:09:51', '2024-05-24 16:09:51', 0);

-- ----------------------------
-- Table structure for post_favour
-- ----------------------------
DROP TABLE IF EXISTS `post_favour`;
CREATE TABLE `post_favour`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `postId` bigint NOT NULL COMMENT '帖子 id',
  `userId` bigint NOT NULL COMMENT '创建用户 id',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_postId`(`postId` ASC) USING BTREE,
  INDEX `idx_userId`(`userId` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '帖子收藏' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of post_favour
-- ----------------------------

-- ----------------------------
-- Table structure for post_thumb
-- ----------------------------
DROP TABLE IF EXISTS `post_thumb`;
CREATE TABLE `post_thumb`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `postId` bigint NOT NULL COMMENT '帖子 id',
  `userId` bigint NOT NULL COMMENT '创建用户 id',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_postId`(`postId` ASC) USING BTREE,
  INDEX `idx_userId`(`userId` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '帖子点赞' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of post_thumb
-- ----------------------------
INSERT INTO `post_thumb` VALUES (1, 19, 1744901815402741761, '2024-05-27 10:41:49', '2024-05-27 10:41:53');

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '标题',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '内容',
  `tags` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '标签列表（json 数组）',
  `answer` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '题解',
  `submitNum` int NOT NULL DEFAULT 0 COMMENT '题目提交数',
  `acceptedNum` int NOT NULL DEFAULT 0 COMMENT '题目通过数',
  `judgeCase` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '判题用例（json 数组）',
  `judgeConfig` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '判题配置（json 对象）',
  `thumbNum` int NOT NULL DEFAULT 0 COMMENT '点赞数',
  `favourNum` int NOT NULL DEFAULT 0 COMMENT '收藏数',
  `userId` bigint NOT NULL COMMENT '创建用户 id',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `isDelete` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_userId`(`userId` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1782304631221833729 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '题目' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO `question` VALUES (1, 'A+B', '题目内容', '[\"简单\"]', '暴力', 0, 0, '[{\"input\":\"1 1\",\"output\":\"2\"}]', '{\"timeLimit\":1000,\"memoryLimit\":1000,\"stackLimit\":1000}', 0, 0, 1744901815402741761, '2024-01-13 20:43:06', '2024-04-15 11:46:07', 0);
INSERT INTO `question` VALUES (1746151836467027969, 'hello', '输出输入姓名输出hello+姓名', '[\"简单\"]', '暴力', 0, 0, '[{\"input\":\"hew\",\"output\":\"hello hew\"}]', '{\"timeLimit\":1000,\"memoryLimit\":1000,\"stackLimit\":1000}', 0, 0, 1744901815402741761, '2024-01-13 20:46:54', '2024-07-29 10:22:41', 0);
INSERT INTO `question` VALUES (1750375106024189953, 'A+C', '#### 数据访问\n\n1. vue中标签中的静态页面通过{{ 变量名}}来访问<script>标签中定义的变量。\n\n2. vue中使用 `v-model=\"变量名\"`实现将model的数据绑定到<script>中定义的变量中。', '[\"新\"]', '新', 0, 0, '[{\"input\":\"1 2\",\"output\":\"3\"}]', '{\"timeLimit\":1,\"memoryLimit\":1,\"stackLimit\":1}', 0, 0, 1744901815402741761, '2024-01-13 20:56:30', '2024-03-29 10:37:25', 0);
INSERT INTO `question` VALUES (1750375106024189954, 'a+b', 'a+b?', '[\"暴力\"]', '暴力破解', 0, 0, NULL, NULL, 0, 0, 1744901815402741761, '2024-01-25 12:28:40', '2024-01-25 12:28:40', 0);
INSERT INTO `question` VALUES (1750375283900428289, 'a+b', 'a+b?', '[\"暴力\"]', '暴力破解', 0, 0, NULL, NULL, 0, 0, 1744901815402741761, '2024-01-25 12:29:22', '2024-01-25 12:29:22', 0);
INSERT INTO `question` VALUES (1750401370432876545, 'sasa', 'sasa', '[]', 'sas', 0, 0, NULL, NULL, 0, 0, 1744901815402741761, '2024-01-25 14:13:02', '2024-03-29 10:37:25', 0);
INSERT INTO `question` VALUES (1750402427267448834, 'sasa', 'sasa', '[]', 'sasa', 0, 0, NULL, NULL, 0, 0, 1744901815402741761, '2024-01-25 14:17:14', '2024-03-29 10:37:25', 0);
INSERT INTO `question` VALUES (1750429702935535618, 'sasa', 'sasa', '[]', 'sasa', 0, 0, NULL, NULL, 0, 0, 1744901815402741761, '2024-01-25 16:05:37', '2024-03-29 10:37:25', 0);
INSERT INTO `question` VALUES (1750431521854185473, 'sdds', 'dsds', '[\"dsds\"]', 'dsd', 0, 0, NULL, NULL, 0, 0, 1744901815402741761, '2024-01-25 16:12:50', '2024-01-25 16:12:50', 0);
INSERT INTO `question` VALUES (1750436661273292801, 'dsdsd', 'dsds', '[\"dsds\"]', 'dsds', 0, 0, NULL, NULL, 0, 0, 1744901815402741761, '2024-01-25 16:33:16', '2024-01-25 16:33:16', 0);
INSERT INTO `question` VALUES (1750437586914230274, 'aaa', 'aaa', '[\"aaa\"]', 'aaa', 0, 0, '[{\"input\":\"aaa\",\"output\":\"aaa\"}]', '{\"timeLimit\":998,\"memoryLimit\":998,\"stackLimit\":998}', 0, 0, 1744901815402741761, '2024-01-25 16:36:56', '2024-01-25 16:36:56', 0);
INSERT INTO `question` VALUES (1750744468266602497, 'dsds', 'dsds', '[\"dsdsd\",\"dsds\"]', 'dsds', 0, 0, '[{\"input\":\"dsds\",\"output\":\"dsds\"}]', '{\"timeLimit\":1002,\"memoryLimit\":1002,\"stackLimit\":1002}', 0, 0, 1744901815402741761, '2024-01-26 12:56:23', '2024-01-26 12:56:23', 0);
INSERT INTO `question` VALUES (1750744476537769985, 'dsds', 'dsds', '[\"dsdsd\",\"dsds\"]', 'dsds', 0, 0, '[{\"input\":\"dsds\",\"output\":\"dsds\"}]', '{\"timeLimit\":1002,\"memoryLimit\":1002,\"stackLimit\":1002}', 0, 0, 1744901815402741761, '2024-01-26 12:56:25', '2024-01-26 12:56:25', 0);
INSERT INTO `question` VALUES (1750744501632290817, 'dsds', 'dsds', '[\"dsdsd\",\"dsds\"]', 'dsds', 0, 0, '[{\"input\":\"dsds\",\"output\":\"ds\"}]', '{\"timeLimit\":1002,\"memoryLimit\":996,\"stackLimit\":1002}', 0, 0, 1744901815402741761, '2024-01-26 12:56:31', '2024-01-26 13:22:40', 0);
INSERT INTO `question` VALUES (1750794714967363586, '111', '111', '[\"111\",\"222\"]', '111', 0, 0, '[{\"input\":\"111\",\"output\":\"111\"}]', '{\"timeLimit\":1000,\"memoryLimit\":1000,\"stackLimit\":1000}', 0, 0, 1744901815402741761, '2024-01-26 16:16:02', '2024-01-26 16:16:02', 0);
INSERT INTO `question` VALUES (1774639472449691649, '两数之和', 'sdsds', '[\"简单\"]', 'asasas', 0, 0, '[{\"input\":\"1 1\",\"output\":\"2\"}]', '{\"timeLimit\":1000,\"memoryLimit\":1000,\"stackLimit\":1000}', 0, 0, 1744901815402741761, '2024-04-01 11:26:36', '2024-04-01 11:26:36', 0);
INSERT INTO `question` VALUES (1782304631221833729, 'test', 'sasa', '[]', 'sasa', 0, 0, '[{\"input\":\"sas\",\"output\":\"sasa\"},{\"input\":\"sas\",\"output\":\"sasa\"}]', '{\"timeLimit\":1000,\"memoryLimit\":1000,\"stackLimit\":1000}', 0, 0, 1744901815402741761, '2024-04-22 15:05:12', '2024-04-22 15:05:12', 0);

-- ----------------------------
-- Table structure for question_answer
-- ----------------------------
DROP TABLE IF EXISTS `question_answer`;
CREATE TABLE `question_answer`  (
  `id` bigint NOT NULL COMMENT 'id',
  `questionId` bigint NOT NULL COMMENT '题目id',
  `userId` bigint NOT NULL COMMENT '用户id',
  `answer` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '题解内容',
  `lookCnt` int NOT NULL DEFAULT 0 COMMENT '查看人数',
  `likeCnt` int NOT NULL DEFAULT 0 COMMENT '点赞人数',
  `commentCnt` int NULL DEFAULT 0 COMMENT '评论数',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isDelete` tinyint NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  `title` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '标题',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '题目题解表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of question_answer
-- ----------------------------
INSERT INTO `question_answer` VALUES (1783319299596853250, 1, 2, '# sasa\n```java\npublic static void main(){}\n```\n', 1, 2, 0, '2024-04-25 10:17:08', '2024-04-25 10:17:08', 0, 'test title');
INSERT INTO `question_answer` VALUES (1783398992136216577, 1746151836467027969, 1797639081053741057, '# sasa\n```java\npublic static void main(){}\n```\n', 1, 2, 0, '2024-04-25 15:33:48', '2024-04-25 15:33:48', 0, 'test title');
INSERT INTO `question_answer` VALUES (1783401980166672385, 1746151836467027969, 1797639081053741057, '# sasa\n```java\npublic static void main(){}\n```\n', 1, 2, 0, '2024-04-25 15:45:40', '2024-04-25 15:45:40', 0, 'test title');
INSERT INTO `question_answer` VALUES (1783401980166672386, 1746151836467027969, 1797639081053741057, '# sasa\n```java\npublic static void main(){}\n```\n', 1, 2, 0, '2024-04-25 15:45:40', '2024-04-25 15:45:40', 0, 'test title');
INSERT INTO `question_answer` VALUES (1783402046419988482, 1746151836467027969, 1797639081053741057, '# sasa\n```java\npublic static void main(){}\n```\n', 1, 2, 0, '2024-04-25 15:45:56', '2024-04-25 15:45:56', 0, 'test title');
INSERT INTO `question_answer` VALUES (1783417053727752194, 1746151836467027969, 1797639081053741057, 'sdsds\ndsdsd', 1, 2, 0, '2024-04-25 16:45:34', '2024-04-25 16:45:34', 0, 'test title');

-- ----------------------------
-- Table structure for question_submit
-- ----------------------------
DROP TABLE IF EXISTS `question_submit`;
CREATE TABLE `question_submit`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `language` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '编程语言',
  `code` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户代码',
  `judgeInfo` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '判题信息（json 对象）',
  `status` int NOT NULL DEFAULT 0 COMMENT '判题状态（0 - 待判题、1 - 判题中、2 - 成功、3 - 失败）',
  `questionId` bigint NOT NULL COMMENT '题目 id',
  `userId` bigint NOT NULL COMMENT '创建用户 id',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `isDelete` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_questionId`(`questionId` ASC) USING BTREE,
  INDEX `idx_userId`(`userId` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1826567326759243778 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '题目提交' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of question_submit
-- ----------------------------
INSERT INTO `question_submit` VALUES (1746158271343050753, 'java', '1+2=3', '{}', 0, 1746154254541930497, 1, '2024-01-13 21:12:28', '2024-01-13 22:17:59', 0);
INSERT INTO `question_submit` VALUES (1746170493423316993, 'cpp', '1+2=3', '{}', 0, 1746154254541930497, 2, '2024-01-13 22:01:02', '2024-01-13 22:17:59', 0);
INSERT INTO `question_submit` VALUES (1746170504022323201, 'cpp', '1+2=3', '{}', 0, 1746154254541930497, 3, '2024-01-13 22:01:05', '2024-01-13 22:17:59', 0);
INSERT INTO `question_submit` VALUES (1746170506643763201, 'cpp', '1+2=3', '{}', 0, 1746154254541930497, 4, '2024-01-13 22:01:05', '2024-01-13 22:17:59', 0);
INSERT INTO `question_submit` VALUES (1746170508942241793, 'cpp', '1+2=3', '{}', 0, 1746154254541930497, 2, '2024-01-13 22:01:06', '2024-01-13 22:17:59', 0);
INSERT INTO `question_submit` VALUES (1746170510385082369, 'cpp', '1+2=3', '{}', 0, 1746154254541930497, 1, '2024-01-13 22:01:06', '2024-01-13 22:17:59', 0);
INSERT INTO `question_submit` VALUES (1763045654348828673, 'java', 'dasdadsa', '{}', 0, 1746154254541930497, 1744901815402741761, '2024-02-29 11:36:54', '2024-02-29 11:36:54', 0);
INSERT INTO `question_submit` VALUES (1763469814720049154, 'java', 'sasas', '{}', 0, 1746154254541930497, 1744901815402741761, '2024-03-01 15:42:22', '2024-03-01 15:42:22', 0);
INSERT INTO `question_submit` VALUES (1763472727773265922, 'java', 'sasassas', '{}', 0, 1746154254541930497, 1744901815402741761, '2024-03-01 15:53:56', '2024-03-01 15:53:56', 0);
INSERT INTO `question_submit` VALUES (1763859282165661698, 'java', 'sdsds', '{}', 1, 1746154254541930497, 1744901815402741761, '2024-03-02 17:29:58', '2024-03-02 17:29:58', 0);
INSERT INTO `question_submit` VALUES (1763859711515590657, 'java', 'sdsdsdfd', '{}', 1, 1746154254541930497, 1744901815402741761, '2024-03-02 17:31:40', '2024-03-02 17:31:40', 0);
INSERT INTO `question_submit` VALUES (1763859960757911553, 'java', 'sdsdsdfd', '{}', 1, 1746154254541930497, 1744901815402741761, '2024-03-02 17:32:40', '2024-03-02 17:32:40', 0);
INSERT INTO `question_submit` VALUES (1763860122599411713, 'java', 'sdsdsdfdg', '{}', 1, 1746154254541930497, 1744901815402741761, '2024-03-02 17:33:18', '2024-03-02 17:33:18', 0);
INSERT INTO `question_submit` VALUES (1763860802621923330, 'java', 'sdsdsdfdg', '{}', 1, 1746154254541930497, 1744901815402741761, '2024-03-02 17:36:00', '2024-03-02 17:36:29', 0);
INSERT INTO `question_submit` VALUES (1763861916121559041, 'java', 'sdsdsdfdg', '{\"message\":\"Wrong Answer\",\"memory\":100,\"time\":100}', 2, 1746154254541930497, 1744901815402741761, '2024-03-02 17:40:26', '2024-03-02 17:40:44', 0);
INSERT INTO `question_submit` VALUES (1772090924051083266, 'java', 'dsfsadf', '{\"message\":\"Wrong Answer\",\"memory\":0,\"time\":-1}', 2, 1746154254541930497, 1744901815402741761, '2024-03-25 10:39:34', '2024-03-25 10:39:35', 0);
INSERT INTO `question_submit` VALUES (1772091540328558594, 'java', 'import java.io.BufferedReader;\r\nimport java.io.File;\r\nimport java.io.InputStreamReader;\r\nimport java.nio.file.Files;\r\nimport java.nio.file.Paths;\r\nimport java.util.Arrays;\r\n\r\npublic class Main {\r\n    public static void main(String[] args) {\r\n        int a = Integer.parseInt(args[0]);\r\n        int b = Integer.parseInt(args[1]);\r\n        System.out.println(\"结果:\" + (a + b));\r\n    }\r\n}\r\n', '{\"message\":\"Wrong Answer\",\"memory\":0,\"time\":-1}', 2, 1746154254541930497, 1744901815402741761, '2024-03-25 10:42:01', '2024-03-25 10:42:02', 0);
INSERT INTO `question_submit` VALUES (1772091974027980802, 'java', 'import java.io.BufferedReader;\r\nimport java.io.File;\r\nimport java.io.InputStreamReader;\r\nimport java.nio.file.Files;\r\nimport java.nio.file.Paths;\r\nimport java.util.Arrays;\r\n\r\npublic class Main {\r\n    public static void main(String[] args) {\r\n        int a = Integer.parseInt(args[0]);\r\n        int b = Integer.parseInt(args[1]);\r\n        System.out.println(\"结果:\" + (a + b));\r\n    }\r\n}\r\n', '{\"message\":\"Wrong Answer\",\"memory\":0,\"time\":-1}', 2, 1746154254541930497, 1744901815402741761, '2024-03-25 10:43:45', '2024-03-25 10:43:45', 0);
INSERT INTO `question_submit` VALUES (1772092738133700609, 'java', 'public class Main {\r\n    public static void main(String[] args) {\r\n        int a = Integer.parseInt(args[0]);\r\n        int b = Integer.parseInt(args[1]);\r\n        System.out.println(\"结果:\" + (a + b));\r\n    }\r\n}\r\n', '{\"message\":\"Wrong Answer\",\"memory\":0,\"time\":-1}', 2, 1746154254541930497, 1744901815402741761, '2024-03-25 10:46:47', '2024-03-25 10:47:27', 0);
INSERT INTO `question_submit` VALUES (1772093231618732034, 'java', 'public class Main {\r\n    public static void main(String[] args) {\r\n        int a = Integer.parseInt(args[0]);\r\n        int b = Integer.parseInt(args[1]);\r\n        System.out.println(\"结果:\" + (a + b));\r\n    }\r\n}\r\n', '{\"message\":\"Wrong Answer\",\"memory\":0,\"time\":-1}', 2, 1746154254541930497, 1744901815402741761, '2024-03-25 10:48:44', '2024-03-25 10:48:45', 0);
INSERT INTO `question_submit` VALUES (1772094545513926657, 'java', 'public class Main {\r\n    public static void main(String[] args) {\r\n        int a = Integer.parseInt(args[0]);\r\n        int b = Integer.parseInt(args[1]);\r\n        System.out.println(\"结果:\" + (a + b));\r\n    }\r\n}\r\n', '{}', 1, 1746154254541930497, 1744901815402741761, '2024-03-25 10:53:58', '2024-03-25 10:53:58', 0);
INSERT INTO `question_submit` VALUES (1772095281362542594, 'java', 'public class Main {\r\n    public static void main(String[] args) {\r\n        int a = Integer.parseInt(args[0]);\r\n        int b = Integer.parseInt(args[1]);\r\n        System.out.println(\"结果:\" + (a + b));\r\n    }\r\n}\r\n', '{\"message\":\"Wrong Answer\",\"memory\":0,\"time\":35}', 2, 1746154254541930497, 1744901815402741761, '2024-03-25 10:56:53', '2024-03-25 10:56:54', 0);
INSERT INTO `question_submit` VALUES (1772095820703899649, 'java', 'public class Main {\r\n    public static void main(String[] args) {\r\n        int a = Integer.parseInt(args[0]);\r\n        int b = Integer.parseInt(args[1]);\r\n        System.out.println(\"结果:\" + (a + b));\r\n    }\r\n}\r\n', '{}', 1, 1746154254541930497, 1744901815402741761, '2024-03-25 10:59:02', '2024-03-25 10:59:02', 0);
INSERT INTO `question_submit` VALUES (1772095982780194817, 'java', 'public class Main {\r\n    public static void main(String[] args) {\r\n        int a = Integer.parseInt(args[0]);\r\n        int b = Integer.parseInt(args[1]);\r\n        System.out.println(\"结果:\" + (a + b));\r\n    }\r\n}\r\n', '{\"message\":\"Wrong Answer\",\"memory\":0,\"time\":36}', 2, 1746154254541930497, 1744901815402741761, '2024-03-25 10:59:40', '2024-03-25 11:02:21', 0);
INSERT INTO `question_submit` VALUES (1772096602392141826, 'java', 'public class Main {\r\n    public static void main(String[] args) {\r\n        int a = Integer.parseInt(args[0]);\r\n        int b = Integer.parseInt(args[1]);\r\n        System.out.println(a + b);\r\n    }\r\n}\r\n', '{\"message\":\"Accepted\",\"memory\":0,\"time\":97}', 2, 1746154254541930497, 1744901815402741761, '2024-03-25 11:02:08', '2024-03-25 11:02:21', 0);
INSERT INTO `question_submit` VALUES (1772096708415758337, 'java', 'public class Main {\r\n    public static void main(String[] args) {\r\n        int a = Integer.parseInt(args[0]);\r\n        int b = Integer.parseInt(args[1]);\r\n        System.out.println(a + b);\r\n    }\r\n}\r\n', '{\"message\":\"Accepted\",\"memory\":0,\"time\":37}', 2, 1746154254541930497, 1744901815402741761, '2024-03-25 11:02:33', '2024-03-25 11:02:34', 0);
INSERT INTO `question_submit` VALUES (1772098004275339266, 'java', 'public class Main {\r\n    public static void main(String[] args) {\r\n        int a = Integer.parseInt(args[0]);\r\n        int b = Integer.parseInt(args[1]);\r\n        System.out.println(a + b);\r\n    }\r\n}\r\n', '{\"message\":\"Accepted\",\"memory\":0,\"time\":42}', 2, 1746154254541930497, 1744901815402741761, '2024-03-25 11:07:42', '2024-03-25 11:07:43', 0);
INSERT INTO `question_submit` VALUES (1772446434902347778, 'java', 'import java.io.BufferedReader;\r\nimport java.io.File;\r\nimport java.io.InputStreamReader;\r\nimport java.nio.file.Files;\r\nimport java.nio.file.Paths;\r\nimport java.util.Arrays;\r\n\r\npublic class Main {\r\n    public static void main(String[] args) {\r\n        int a = Integer.parseInt(args[0]);\r\n        int b = Integer.parseInt(args[1]);\r\n        System.out.println(\"结果:\" + (a + b));\r\n    }\r\n}\r\n', '{\"message\":\"Wrong Answer\",\"memory\":0,\"time\":69}', 2, 1746154254541930497, 1744901815402741761, '2024-03-26 10:12:15', '2024-03-26 10:12:15', 0);
INSERT INTO `question_submit` VALUES (1773615601319735298, 'java', 'int main', '{}', 0, 1, 1744901815402741761, '2024-03-29 15:38:06', '2024-03-29 15:38:06', 0);
INSERT INTO `question_submit` VALUES (1774642957346009089, 'java', 'import java.io.BufferedReader;\r\nimport java.io.File;\r\nimport java.io.InputStreamReader;\r\nimport java.nio.file.Files;\r\nimport java.nio.file.Paths;\r\nimport java.util.Arrays;\r\n\r\npublic class Main {\r\n    public static void main(String[] args) {\r\n        int a = Integer.parseInt(args[0]);\r\n        int b = Integer.parseInt(args[1]);\r\n        System.out.println(\"结果:\" + (a + b));\r\n    }\r\n}', '{\"message\":\"Wrong Answer\",\"memory\":0,\"time\":81}', 2, 1774639472449691649, 1744901815402741761, '2024-04-01 11:40:26', '2024-04-01 11:40:27', 0);
INSERT INTO `question_submit` VALUES (1774643089852461058, 'java', 'import java.io.BufferedReader;\r\nimport java.io.File;\r\nimport java.io.InputStreamReader;\r\nimport java.nio.file.Files;\r\nimport java.nio.file.Paths;\r\nimport java.util.Arrays;\r\n\r\npublic class Main {\r\n    public static void main(String[] args) {\r\n        int a = Integer.parseInt(args[0]);\r\n        int b = Integer.parseInt(args[1]);\r\n        System.out.println((a + b));\r\n    }\r\n}', '{\"message\":\"Accepted\",\"memory\":0,\"time\":76}', 2, 1774639472449691649, 1744901815402741761, '2024-04-01 11:40:58', '2024-04-01 11:40:59', 0);
INSERT INTO `question_submit` VALUES (1774644843994161153, 'java', 'import java.io.BufferedReader;\r\nimport java.io.File;\r\nimport java.io.InputStreamReader;\r\nimport java.nio.file.Files;\r\nimport java.nio.file.Paths;\r\nimport java.util.Arrays;\r\n\r\npublic class Main {\r\n    public static void main(String[] args) {\r\n        int a = Integer.parseInt(args[0]);\r\n        int b = Integer.parseInt(args[1]);\r\n        System.out.println((a + b));\r\n    }\r\n}', '{\"message\":\"Accepted\",\"memory\":0,\"time\":75}', 2, 1774639472449691649, 1744901815402741761, '2024-04-01 11:47:56', '2024-04-01 11:47:57', 0);
INSERT INTO `question_submit` VALUES (1779685870098604034, 'java', 'import java.io.BufferedReader;\r\nimport java.io.File;\r\nimport java.io.InputStreamReader;\r\nimport java.nio.file.Files;\r\nimport java.nio.file.Paths;\r\nimport java.util.Arrays;\r\n\r\npublic class Main {\r\n    public static void main(String[] args) {\r\n        int a = Integer.parseInt(args[0]);\r\n        int b = Integer.parseInt(args[1]);\r\n        System.out.println((a + b));\r\n    }\r\n}\r\n', '{}', 1, 1, 1744901815402741761, '2024-04-15 09:39:11', '2024-04-15 09:39:11', 0);
INSERT INTO `question_submit` VALUES (1779717736478433281, 'java', 'import java.io.BufferedReader;\r\nimport java.io.File;\r\nimport java.io.InputStreamReader;\r\nimport java.nio.file.Files;\r\nimport java.nio.file.Paths;\r\nimport java.util.Arrays;\r\n\r\npublic class Main {\r\n    public static void main(String[] args) {\r\n        int a = Integer.parseInt(args[0]);\r\n        int b = Integer.parseInt(args[1]);\r\n        System.out.println(\"结果:\" + (a + b));\r\n    }\r\n}\r\n', '{}', 1, 1, 1744901815402741761, '2024-04-15 11:45:48', '2024-04-15 11:45:48', 0);
INSERT INTO `question_submit` VALUES (1779853889453969410, 'java', 'import java.io.BufferedReader;\r\nimport java.io.File;\r\nimport java.io.InputStreamReader;\r\nimport java.nio.file.Files;\r\nimport java.nio.file.Paths;\r\nimport java.util.Arrays;\r\n\r\npublic class Main {\r\n    public static void main(String[] args) {\r\n        int a = Integer.parseInt(args[0]);\r\n        int b = Integer.parseInt(args[1]);\r\n        System.out.println((a + b));\r\n    }\r\n}\r\n', '{\"message\":\"Accepted\",\"memory\":0,\"time\":73}', 2, 1, 1779768810514046978, '2024-04-15 20:46:49', '2024-04-15 20:46:50', 0);
INSERT INTO `question_submit` VALUES (1780067975332749313, 'java', '', '{\"message\":\"Wrong Answer\",\"memory\":0,\"time\":-1}', 2, 1, 1744901815402741761, '2024-04-16 10:57:32', '2024-04-16 10:57:33', 0);
INSERT INTO `question_submit` VALUES (1780146958720286721, 'java', '', '{\"message\":\"Wrong Answer\",\"memory\":0,\"time\":-1}', 2, 1, 1744901815402741761, '2024-04-16 16:11:23', '2024-04-16 16:11:23', 0);
INSERT INTO `question_submit` VALUES (1780147166598381569, 'java', '', '{\"message\":\"Wrong Answer\",\"memory\":0,\"time\":-1}', 2, 1, 1744901815402741761, '2024-04-16 16:12:12', '2024-04-16 16:12:13', 0);
INSERT INTO `question_submit` VALUES (1782222206227582978, 'java', 'fsdf', '{\"message\":\"Wrong Answer\",\"memory\":0,\"time\":-1}', 2, 1, 1744901815402741761, '2024-04-22 09:37:40', '2024-04-22 09:38:12', 0);
INSERT INTO `question_submit` VALUES (1790038338482233346, 'java', 'sdsd', '{}', 1, 1746151836467027969, 1744901815402741761, '2024-05-13 23:16:11', '2024-05-13 23:16:11', 0);
INSERT INTO `question_submit` VALUES (1793868880663289858, 'java', 'asasa', '{}', 1, 1746151836467027969, 1744901815402741761, '2024-05-24 12:57:24', '2024-05-24 12:57:24', 0);
INSERT INTO `question_submit` VALUES (1797604965612642306, 'java', 'ds', '{}', 1, 1746151836467027969, 1797548379724599297, '2024-06-03 20:23:16', '2024-06-03 20:23:16', 0);
INSERT INTO `question_submit` VALUES (1813197110788583426, 'java', '', '{}', 1, 1746151836467027969, 1800457759336017922, '2024-07-16 21:00:53', '2024-07-16 21:00:53', 0);
INSERT INTO `question_submit` VALUES (1815583432333434881, 'java', 'import java.io.BufferedReader;\r\nimport java.io.File;\r\nimport java.io.InputStreamReader;\r\nimport java.nio.file.Files;\r\nimport java.nio.file.Paths;\r\nimport java.util.Arrays;\r\n\r\npublic class Main {\r\n    public static void main(String[] args) {\r\n        int a = Integer.parseInt(args[0]);\r\n        int b = Integer.parseInt(args[1]);\r\n        System.out.println(\"结果:\" + (a + b));\r\n    }\r\n}\r\n', '{}', 1, 1746151836467027969, 1800457759336017922, '2024-07-23 11:03:16', '2024-07-23 11:03:16', 0);
INSERT INTO `question_submit` VALUES (1815583714836586497, 'java', 'import java.io.BufferedReader;\r\nimport java.io.File;\r\nimport java.io.InputStreamReader;\r\nimport java.nio.file.Files;\r\nimport java.nio.file.Paths;\r\nimport java.util.Arrays;\r\n\r\npublic class Main {\r\n    public static void main(String[] args) {\r\n        int a = Integer.parseInt(args[0]);\r\n        int b = Integer.parseInt(args[1]);\r\n        System.out.println((a + b));\r\n    }\r\n}\r\n', '{}', 1, 1746151836467027969, 1800457759336017922, '2024-07-23 11:04:24', '2024-07-23 11:04:24', 0);
INSERT INTO `question_submit` VALUES (1815584186414768130, 'java', 'import java.io.BufferedReader;\r\nimport java.io.File;\r\nimport java.io.InputStreamReader;\r\nimport java.nio.file.Files;\r\nimport java.nio.file.Paths;\r\nimport java.util.Arrays;\r\n\r\npublic class Main {\r\n    public static void main(String[] args) {\r\n        int a = Integer.parseInt(args[0]);\r\n        int b = Integer.parseInt(args[1]);\r\n        System.out.println(\"结果:\" + (a + b));\r\n    }\r\n}\r\n', '{\"message\":\"Wrong Answer\",\"memory\":0,\"time\":60}', 2, 1, 1800457759336017922, '2024-07-23 11:06:16', '2024-07-23 11:06:17', 0);
INSERT INTO `question_submit` VALUES (1815584246116491265, 'java', 'import java.io.BufferedReader;\r\nimport java.io.File;\r\nimport java.io.InputStreamReader;\r\nimport java.nio.file.Files;\r\nimport java.nio.file.Paths;\r\nimport java.util.Arrays;\r\n\r\npublic class Main {\r\n    public static void main(String[] args) {\r\n        int a = Integer.parseInt(args[0]);\r\n        int b = Integer.parseInt(args[1]);\r\n        System.out.println( (a + b));\r\n    }\r\n}\r\n', '{\"message\":\"Accepted\",\"memory\":0,\"time\":66}', 2, 1, 1800457759336017922, '2024-07-23 11:06:30', '2024-07-23 11:06:31', 0);
INSERT INTO `question_submit` VALUES (1816639695309864962, 'java', 'import java.io.BufferedReader;\r\nimport java.io.File;\r\nimport java.io.InputStreamReader;\r\nimport java.nio.file.Files;\r\nimport java.nio.file.Paths;\r\nimport java.util.Arrays;\r\n\r\npublic class Main {\r\n    public static void main(String[] args) {\r\n        int a = Integer.parseInt(args[0]);\r\n        int b = Integer.parseInt(args[1]);\r\n        System.out.println(\"结果:\" + (a + b));\r\n    }\r\n}\r\n', '{}', 1, 1, 1800457759336017922, '2024-07-26 09:00:29', '2024-07-26 09:00:29', 0);
INSERT INTO `question_submit` VALUES (1816640709404495873, 'java', 'import java.io.BufferedReader;\r\nimport java.io.File;\r\nimport java.io.InputStreamReader;\r\nimport java.nio.file.Files;\r\nimport java.nio.file.Paths;\r\nimport java.util.Arrays;\r\n\r\npublic class Main {\r\n    public static void main(String[] args) {\r\n        int a = Integer.parseInt(args[0]);\r\n        int b = Integer.parseInt(args[1]);\r\n        System.out.println(\"结果:\" + (a + b));\r\n    }\r\n}\r\n', '{}', 1, 1, 1800457759336017922, '2024-07-26 09:04:31', '2024-07-26 09:04:31', 0);
INSERT INTO `question_submit` VALUES (1816640854229639170, 'java', 'import java.io.BufferedReader;\r\nimport java.io.File;\r\nimport java.io.InputStreamReader;\r\nimport java.nio.file.Files;\r\nimport java.nio.file.Paths;\r\nimport java.util.Arrays;\r\n\r\npublic class Main {\r\n    public static void main(String[] args) {\r\n        int a = Integer.parseInt(args[0]);\r\n        int b = Integer.parseInt(args[1]);\r\n        System.out.println(\"结果:\" + (a + b));\r\n    }\r\n}\r\n', '{}', 1, 1, 1800457759336017922, '2024-07-26 09:05:05', '2024-07-26 09:05:05', 0);
INSERT INTO `question_submit` VALUES (1816641617706213378, 'java', 'import java.util.Scanner;\r\n\r\npublic class Main {\r\n    public static void main(String[] args) {\r\n        Scanner scanner = new Scanner(System.in);\r\n        String str = scanner.nextLine();\r\n        int target = scanner.nextInt();\r\n\r\n        String[] split = str.split(\",\");\r\n        int len = split.length;\r\n        int[] nums = new int[len];\r\n\r\n        for (int i = 0; i < len; i++) {\r\n            nums[i] = Integer.parseInt(split[i]);\r\n        }\r\n\r\n        for (int i = 0; i < len; ++i) {\r\n            for (int j = i + 1; j < len; ++j) {\r\n                if (nums[i] + nums[j] == target) {\r\n                    System.out.println(i + \",\" + j);\r\n                    System.exit(0);\r\n                }\r\n            }\r\n        }\r\n\r\n        System.exit(0);\r\n    }\r\n}', '{}', 1, 1, 1800457759336017922, '2024-07-26 09:08:07', '2024-07-26 09:08:07', 0);
INSERT INTO `question_submit` VALUES (1816666386005491714, 'java', 'import java.util.Scanner;\r\n\r\npublic class Main {\r\n    public static void main(String[] args) {\r\n        Scanner scanner = new Scanner(System.in);\r\n        String str = scanner.nextLine();\r\n        int target = scanner.nextInt();\r\n\r\n        String[] split = str.split(\",\");\r\n        int len = split.length;\r\n        int[] nums = new int[len];\r\n\r\n        for (int i = 0; i < len; i++) {\r\n            nums[i] = Integer.parseInt(split[i]);\r\n        }\r\n\r\n        for (int i = 0; i < len; ++i) {\r\n            for (int j = i + 1; j < len; ++j) {\r\n                if (nums[i] + nums[j] == target) {\r\n                    System.out.println(i + \",\" + j);\r\n                    System.exit(0);\r\n                }\r\n            }\r\n        }\r\n\r\n        System.exit(0);\r\n    }\r\n}', '{}', 1, 1, 1800457759336017922, '2024-07-26 10:46:33', '2024-07-26 10:46:33', 0);
INSERT INTO `question_submit` VALUES (1816694014788878337, 'java', 'import java.util.Scanner;\r\n\r\npublic class Main {\r\n    public static void main(String[] args) {\r\n        Scanner scanner = new Scanner(System.in);\r\n        String str = scanner.nextLine();\r\n        int target = scanner.nextInt();\r\n\r\n        String[] split = str.split(\",\");\r\n        int len = split.length;\r\n        int[] nums = new int[len];\r\n\r\n        for (int i = 0; i < len; i++) {\r\n            nums[i] = Integer.parseInt(split[i]);\r\n        }\r\n\r\n        for (int i = 0; i < len; ++i) {\r\n            for (int j = i + 1; j < len; ++j) {\r\n                if (nums[i] + nums[j] == target) {\r\n                    System.out.println(i + \",\" + j);\r\n                    System.exit(0);\r\n                }\r\n            }\r\n        }\r\n\r\n        System.exit(0);\r\n    }\r\n}', '{}', 1, 1, 1800457759336017922, '2024-07-26 12:36:20', '2024-07-26 12:36:20', 0);
INSERT INTO `question_submit` VALUES (1816694014788878338, 'java', 'import java.util.Scanner;\r\n\r\npublic class Main {\r\n    public static void main(String[] args) {\r\n        Scanner scanner = new Scanner(System.in);\r\n        String str = scanner.nextLine();\r\n        System.out.println(str);\r\n        System.exit(0);\r\n    }\r\n}', '{}', 1, 1, 1800457759336017922, '2024-07-26 12:36:20', '2024-07-26 12:36:20', 0);
INSERT INTO `question_submit` VALUES (1817748099285737474, 'java', '\r\nimport java.util.Scanner;\r\npublic class Main {\r\n    public static void main(String[] args) {\r\n        Scanner scanner = new Scanner(System.in);\r\n        String str = scanner.nextLine();\r\n        System.out.println(\"hello \"+str);\r\n         System.exit(0);\r\n    }\r\n}', '{\"message\":\"Wrong Answer\",\"memory\":0,\"time\":-1}', 2, 1746151836467027969, 1800457759336017922, '2024-07-29 10:24:53', '2024-07-29 10:31:51', 0);
INSERT INTO `question_submit` VALUES (1817748691508879361, 'java', 'import java.util.Scanner;\r\npublic class Main {\r\n    public static void main(String[] args) {\r\n        Scanner scanner = new Scanner(System.in);\r\n        String str = scanner.nextLine();   \r\n        System.out.println(\"hello \"+str);\r\n        System.exit(0);\r\n    }\r\n}', '{\"message\":\"Wrong Answer\",\"memory\":0,\"time\":-1}', 2, 1746151836467027969, 1800457759336017922, '2024-07-29 10:27:14', '2024-07-29 10:31:51', 0);
INSERT INTO `question_submit` VALUES (1817749771156283393, 'java', 'import java.util.Scanner;\r\n\r\npublic class Main {\r\n    public static void main(String[] args) {\r\n        Scanner scanner = new Scanner(System.in);\r\n        String str = scanner.nextLine();\r\n        System.out.println(\"hello \"+str);\r\n        System.exit(0);\r\n    }\r\n}', '{\"message\":\"Wrong Answer\",\"memory\":0,\"time\":-1}', 2, 1746151836467027969, 1800457759336017922, '2024-07-29 10:31:32', '2024-07-29 10:31:51', 0);
INSERT INTO `question_submit` VALUES (1817749815502659585, 'java', 'import java.util.Scanner;\r\n\r\npublic class Main {\r\n    public static void main(String[] args) {\r\n        Scanner scanner = new Scanner(System.in);\r\n        String str = scanner.nextLine();\r\n        System.out.println(\"hello \"+str);\r\n        System.exit(0);\r\n    }\r\n}', '{\"message\":\"Wrong Answer\",\"memory\":0,\"time\":-1}', 2, 1746151836467027969, 1800457759336017922, '2024-07-29 10:31:42', '2024-07-29 10:31:51', 0);
INSERT INTO `question_submit` VALUES (1817749932314038274, 'java', 'import java.util.Scanner;\r\n\r\npublic class Main {\r\n    public static void main(String[] args) {\r\n        Scanner scanner = new Scanner(System.in);\r\n        String str = scanner.nextLine();\r\n        System.out.println(\"hello \"+str);\r\n        System.exit(0);\r\n    }\r\n}', '{\"message\":\"Wrong Answer\",\"memory\":0,\"time\":-1}', 2, 1746151836467027969, 1800457759336017922, '2024-07-29 10:32:10', '2024-07-29 10:36:30', 0);
INSERT INTO `question_submit` VALUES (1817752299696553986, 'java', 'import java.util.Scanner;\r\n\r\npublic class Main {\r\n    public static void main(String[] args) {\r\n        Scanner scanner = new Scanner(System.in);\r\n        String str = scanner.nextLine();\r\n        System.out.println(\"hello \"+str);\r\n        System.exit(0);\r\n    }\r\n}', '{}', 1, 1, 1800457759336017922, '2024-07-29 10:41:35', '2024-07-29 10:41:35', 0);
INSERT INTO `question_submit` VALUES (1817752515208392706, 'java', 'import java.util.Scanner;\r\n\r\npublic class Main {\r\n    public static void main(String[] args) {\r\n        Scanner scanner = new Scanner(System.in);\r\n        String str = scanner.nextLine();\r\n        System.out.println(\"hello \"+str);\r\n        System.exit(0);\r\n    }\r\n}', '{\"message\":\"超时\",\"memory\":0,\"time\":14185}', 2, 1746151836467027969, 1800457759336017922, '2024-07-29 10:42:26', '2024-07-29 10:43:29', 0);
INSERT INTO `question_submit` VALUES (1817752969715744770, 'java', 'import java.util.Scanner;\r\n\r\npublic class Main {\r\n    public static void main(String[] args) {\r\n        Scanner scanner = new Scanner(System.in);\r\n        String str = scanner.nextLine();\r\n        System.out.println(\"hello \"+str);\r\n        System.exit(0);\r\n    }\r\n}', '{\"message\":\"Accepted\",\"memory\":0,\"time\":80}', 2, 1746151836467027969, 1800457759336017922, '2024-07-29 10:44:14', '2024-07-29 10:44:15', 0);
INSERT INTO `question_submit` VALUES (1817926071565262849, 'java', 'import java.util.Scanner;\r\n\r\npublic class Main {\r\n    public static void main(String[] args) {\r\n        Scanner scanner = new Scanner(System.in);\r\n        String str = scanner.nextLine();\r\n        System.out.println(\"hello \"+str);\r\n        System.exit(0);\r\n    }\r\n}', '{\"message\":\"Accepted\",\"memory\":0,\"time\":88}', 2, 1746151836467027969, 1800457759336017922, '2024-07-29 22:12:05', '2024-07-29 22:12:06', 0);
INSERT INTO `question_submit` VALUES (1818110258716975106, 'java', 'import java.util.Scanner;\r\n\r\npublic class Main {\r\n    public static void main(String[] args) {\r\n        Scanner scanner = new Scanner(System.in);\r\n        String str = scanner.nextLine();\r\n        System.out.println(\"hello \"+str);\r\n    }\r\n}', '{\"message\":\"Accepted\",\"memory\":0,\"time\":110}', 2, 1746151836467027969, 1800457759336017922, '2024-07-30 10:23:59', '2024-07-30 10:23:59', 0);
INSERT INTO `question_submit` VALUES (1818197732277354498, 'java', 'import java.util.Scanner;\r\n\r\npublic class Main {\r\n    public static void main(String[] args) {\r\n        Scanner scanner = new Scanner(System.in);\r\n        String str = scanner.nextLine();\r\n        System.out.println(\"hello \"+str);\r\n    }\r\n}', '{\"message\":\"Accepted\",\"memory\":0,\"time\":96}', 2, 1746151836467027969, 1800457759336017922, '2024-07-30 16:11:34', '2024-07-30 16:11:35', 0);
INSERT INTO `question_submit` VALUES (1826509670044487681, 'java', 'import java.util.Scanner;\r\n\r\npublic class Main {\r\n    public static void main(String[] args) {\r\n        Scanner scanner = new Scanner(System.in);\r\n        String str = scanner.nextLine();\r\n        System.out.println(\"hello \"+str);\r\n    }\r\n}', '{\"message\":\"Accepted\",\"memory\":0,\"time\":96}', 2, 1746151836467027969, 1800457759336017922, '2024-08-22 14:40:14', '2024-08-22 14:40:15', 0);
INSERT INTO `question_submit` VALUES (1826509851259392001, 'java', 'import java.util.Scanner;\r\n\r\npublic class Main {\r\n    public static void main(String[] args) {\r\n        Scanner scanner = new Scanner(System.in);\r\n        String str = scanner.nextLine();\r\n        while(true){\r\n            System.out.println(\"hello \"+str);\r\n        }\r\n        System.out.println(\"hello \"+str);\r\n    }\r\n}', '{\"message\":\"Wrong Answer\",\"memory\":0,\"time\":-1}', 2, 1746151836467027969, 1800457759336017922, '2024-08-22 14:40:58', '2024-08-22 14:40:58', 0);
INSERT INTO `question_submit` VALUES (1826510212711927809, 'java', 'import java.util.Scanner;\r\n\r\npublic class Main {\r\n    public static void main(String[] args) {\r\n        Scanner scanner = new Scanner(System.in);\r\n        String str = scanner.nextLine();\r\n        while(true){\r\n            System.out.println(\"hello \"+str);\r\n        }\r\n    }\r\n}', '{}', 1, 1746151836467027969, 1800457759336017922, '2024-08-22 14:42:24', '2024-08-22 14:42:24', 0);
INSERT INTO `question_submit` VALUES (1826510819577384961, 'java', 'import java.util.Scanner;\r\n\r\npublic class Main {\r\n    public static void main(String[] args) {\r\n        Scanner scanner = new Scanner(System.in);\r\n        String str = scanner.nextLine();\r\n        while(true){\r\n            System.out.println(\"hello \"+str);\r\n        }\r\n    }\r\n}', '{}', 1, 1746151836467027969, 1800457759336017922, '2024-08-22 14:44:48', '2024-08-22 14:44:48', 0);
INSERT INTO `question_submit` VALUES (1826511185815621634, 'java', 'import java.util.Scanner;\r\n\r\npublic class Main {\r\n    public static void main(String[] args) {\r\n        Scanner scanner = new Scanner(System.in);\r\n        String str = scanner.nextLine();\r\n        while(true){\r\n            System.out.println(\"hello \"+str);\r\n        }\r\n    }\r\n}', '{}', 1, 1746151836467027969, 1800457759336017922, '2024-08-22 14:46:16', '2024-08-22 14:46:16', 0);
INSERT INTO `question_submit` VALUES (1826511409200058369, 'java', 'import java.util.Scanner;\r\n\r\npublic class Main {\r\n    public static void main(String[] args) {\r\n        Scanner scanner = new Scanner(System.in);\r\n        String str = scanner.nextLine();\r\n        while(true){\r\n            System.out.println(\"hello \"+str);\r\n        }\r\n    }\r\n}', '{}', 1, 1746151836467027969, 1800457759336017922, '2024-08-22 14:47:09', '2024-08-22 14:47:09', 0);
INSERT INTO `question_submit` VALUES (1826512261688152065, 'java', 'import java.util.Scanner;\r\n\r\npublic class Main {\r\n    public static void main(String[] args) {\r\n        Scanner scanner = new Scanner(System.in);\r\n        String str = scanner.nextLine();\r\n        while(true){\r\n            System.out.println(\"hello \"+str);\r\n        }\r\n    }\r\n}', '{\"message\":\"Wrong Answer\",\"memory\":0,\"time\":-1}', 2, 1746151836467027969, 1800457759336017922, '2024-08-22 14:50:32', '2024-08-22 14:51:09', 0);
INSERT INTO `question_submit` VALUES (1826565799705399297, 'java', 'import java.util.Scanner;\r\n\r\npublic class Main {\r\n    public static void main(String[] args) {\r\n        Scanner scanner = new Scanner(System.in);\r\n        String str = scanner.nextLine();\r\n        while(true){\r\n            System.out.println(\"hello \"+str);\r\n        }\r\n    }\r\n}', '{\"message\":\"Wrong Answer\",\"memory\":2,\"time\":2004}', 2, 1746151836467027969, 1800457759336017922, '2024-08-22 18:23:17', '2024-08-22 18:23:20', 0);
INSERT INTO `question_submit` VALUES (1826565940499795970, 'java', 'import java.util.Scanner;\r\n\r\npublic class Main {\r\n    public static void main(String[] args) {\r\n        Scanner scanner = new Scanner(System.in);\r\n        String str = scanner.nextLine();\r\n        System.out.println(\"hello \"+str);\r\n    }\r\n}', '{\"message\":\"Accepted\",\"memory\":2,\"time\":80}', 2, 1746151836467027969, 1800457759336017922, '2024-08-22 18:23:50', '2024-08-22 18:23:51', 0);
INSERT INTO `question_submit` VALUES (1826567326759243778, 'java', 'import java.util.Scanner;\r\n\r\npublic class Main {\r\n    public static void main(String[] args) {\r\n        Scanner scanner = new Scanner(System.in);\r\n        String str = scanner.nextLine();\r\n        while(true){\r\n            System.out.println(\"hello \"+str);\r\n        }\r\n    }\r\n}', '{\"message\":\"Wrong Answer\",\"memory\":2,\"time\":2008}', 2, 1746151836467027969, 1800457759336017922, '2024-08-22 18:29:21', '2024-08-22 18:29:24', 0);

-- ----------------------------
-- Table structure for seckill_voucher
-- ----------------------------
DROP TABLE IF EXISTS `seckill_voucher`;
CREATE TABLE `seckill_voucher`  (
  `voucher_id` bigint UNSIGNED NOT NULL COMMENT '关联的优惠券的id',
  `stock` int NOT NULL COMMENT '库存',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `begin_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '生效时间',
  `end_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '失效时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `isDelete` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`voucher_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '秒杀优惠券表，与优惠券是一对一关系' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of seckill_voucher
-- ----------------------------
INSERT INTO `seckill_voucher` VALUES (10, 100, '2024-05-16 17:52:47', '2024-05-16 17:52:47', '2024-10-06 09:43:31', '2024-05-22 20:17:58', 0);
INSERT INTO `seckill_voucher` VALUES (11, 300, '2024-05-16 18:03:38', '2024-05-16 18:03:38', '2024-06-06 09:43:31', '2024-05-16 18:03:38', 0);
INSERT INTO `seckill_voucher` VALUES (12, 300, '2024-05-16 19:14:04', '2024-05-16 19:14:04', '2024-06-06 09:43:31', '2024-05-16 19:14:04', 0);
INSERT INTO `seckill_voucher` VALUES (13, 100, '2024-05-16 19:22:00', '2024-05-16 19:22:00', '2024-06-06 09:43:31', '2024-06-27 17:26:32', 0);
INSERT INTO `seckill_voucher` VALUES (14, 999, '2024-05-22 15:25:25', '2024-05-16 17:52:47', '2024-10-06 09:43:31', '2024-06-27 17:41:43', 0);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `userAccount` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '账号',
  `userPassword` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
  `unionId` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '微信开放平台id',
  `mpOpenId` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '公众号openId',
  `userName` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `userAvatar` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户头像',
  `userProfile` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户简介',
  `userRole` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'user' COMMENT '用户角色：user/admin/ban',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `isDelete` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_unionId`(`unionId` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1800457759336017922 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1744901815402741761, 'yupi', 'b0dd3697a192885d7c055db46155b26a', NULL, NULL, 'yuoi', 'http://192.168.68.132:9000/hewoj-minio/defaultUserAvatar.jpg', NULL, 'user', '2024-01-10 09:59:46', '2024-06-06 21:50:44', 0);
INSERT INTO `user` VALUES (1779768810514046978, 'hewei', 'b0dd3697a192885d7c055db46155b26a', NULL, NULL, 'hewei', 'http://192.168.68.132:9000/hewoj-minio/defaultUserAvatar.jpg', NULL, 'user', '2024-04-15 15:08:45', '2024-06-06 21:50:44', 0);
INSERT INTO `user` VALUES (1779769618563489793, 'test', 'b0dd3697a192885d7c055db46155b26a', NULL, NULL, NULL, 'http://192.168.68.132:9000/hewoj-minio/defaultUserAvatar.jpg', NULL, 'user', '2024-04-15 15:11:58', '2024-06-06 21:50:44', 0);
INSERT INTO `user` VALUES (1779770810165243905, 'test2', 'b0dd3697a192885d7c055db46155b26a', NULL, NULL, NULL, 'http://192.168.68.132:9000/hewoj-minio/defaultUserAvatar.jpg', NULL, 'user', '2024-04-15 15:16:42', '2024-06-06 21:50:44', 0);
INSERT INTO `user` VALUES (1779772088211615746, 'test3', 'b0dd3697a192885d7c055db46155b26a', NULL, NULL, NULL, 'http://192.168.68.132:9000/hewoj-minio/defaultUserAvatar.jpg', NULL, 'user', '2024-04-15 15:21:47', '2024-06-06 21:50:44', 0);
INSERT INTO `user` VALUES (1779843967073005570, 'test4', 'b0dd3697a192885d7c055db46155b26a', NULL, NULL, NULL, 'http://192.168.68.132:9000/hewoj-minio/defaultUserAvatar.jpg', NULL, 'user', '2024-04-15 20:07:24', '2024-06-06 21:50:44', 0);
INSERT INTO `user` VALUES (1797548379724599297, 'hew@@@', 'b0dd3697a192885d7c055db46155b26a', NULL, NULL, 'hew', 'http://192.168.68.132:9000/hewoj-minio/defaultUserAvatar.jpg', NULL, 'user', '2024-06-03 16:38:25', '2024-06-06 21:50:44', 0);
INSERT INTO `user` VALUES (1797639081053741057, '316@qq.com', 'b0dd3697a192885d7c055db46155b26a', NULL, NULL, '何苇', 'http://192.168.68.132:9000/hewoj-minio/1718099295538.jpg', '菜鸡一枚', 'user', '2024-06-03 22:38:50', '2024-06-11 17:48:31', 0);
INSERT INTO `user` VALUES (1797645030552281089, '123@qq.com', 'b0dd3697a192885d7c055db46155b26a', NULL, NULL, '小明', 'http://192.168.68.132:9000/hewoj-minio/defaultUserAvatar.jpg', '我叫小明', 'user', '2024-06-03 23:02:28', '2024-06-03 23:02:28', 0);
INSERT INTO `user` VALUES (1800457759336017922, 'admin@qq.com', 'b0dd3697a192885d7c055db46155b26a', NULL, NULL, '管理员', 'http://192.168.68.132:9000/hewoj-minio/defaultUserAvatar.jpg', '管理员', 'admin', '2024-06-11 17:19:15', '2024-06-11 17:19:15', 0);

-- ----------------------------
-- Table structure for voucher
-- ----------------------------
DROP TABLE IF EXISTS `voucher`;
CREATE TABLE `voucher`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '代金券标题',
  `sub_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '副标题',
  `rules` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '使用规则',
  `pay_value` bigint UNSIGNED NOT NULL COMMENT '支付金额，单位是分。例如200代表2元',
  `actual_value` bigint NOT NULL COMMENT '抵扣金额，单位是分。例如200代表2元',
  `type` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '0,普通券；1,秒杀券',
  `status` tinyint UNSIGNED NOT NULL DEFAULT 1 COMMENT '1,上架; 2,下架; 3,过期',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `isDelete` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'vip优惠卷表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of voucher
-- ----------------------------
INSERT INTO `voucher` VALUES (1, '50元代金券', '周一至周日均可使用', '全场通用\\n无需预约\\n可无限叠加\\不兑现、不找零\\n仅限堂食', 4750, 5000, 0, 1, '2022-01-04 09:42:39', '2022-01-04 09:43:31', 0);
INSERT INTO `voucher` VALUES (10, '50元代金券', '周一至周日均可使用', '全场通用\n无需预约\n可无限叠加\n不兑现、不找零\n仅限堂食', 200, 20, 1, 1, '2024-05-16 17:52:47', '2024-06-27 16:03:19', 0);
INSERT INTO `voucher` VALUES (11, '50元代金券', '周一至周日均可使用', '全场通用\n无需预约\n可无限叠加\n不兑现、不找零\n仅限堂食', 200, 20, 1, 1, '2024-05-16 18:03:26', '2024-06-27 16:03:19', 0);
INSERT INTO `voucher` VALUES (12, '100元代金券', '周一至周日均可使用', '全场通用\n无需预约\n可无限叠加\n不兑现、不找零\n仅限堂食', 200, 20, 1, 1, '2024-05-16 19:14:00', '2024-06-27 16:03:19', 0);
INSERT INTO `voucher` VALUES (13, '1000元代金券', '周一至周日均可使用', '全场通用\n无需预约\n可无限叠加\n不兑现、不找零\n仅限堂食', 200, 20, 1, 1, '2024-05-16 19:21:55', '2024-06-27 16:03:19', 0);
INSERT INTO `voucher` VALUES (14, '50元代金券', '周一至周日均可使用', '全场通用无需预约可无限叠加不兑现、不找零仅限堂', 200, 10000, 0, 1, '2024-05-16 17:52:47', '2024-05-22 15:25:25', 0);

-- ----------------------------
-- Table structure for voucher_order
-- ----------------------------
DROP TABLE IF EXISTS `voucher_order`;
CREATE TABLE `voucher_order`  (
  `id` bigint NOT NULL COMMENT '主键',
  `userId` bigint UNSIGNED NOT NULL COMMENT '下单的用户id',
  `voucherId` bigint UNSIGNED NOT NULL COMMENT '购买的代金券id',
  `payType` tinyint UNSIGNED NOT NULL DEFAULT 1 COMMENT '支付方式 1：余额支付；2：支付宝；3：微信',
  `status` tinyint UNSIGNED NOT NULL DEFAULT 1 COMMENT '订单状态，1：未支付；2：已支付；3：已核销；4：已取消；5：退款中；6：已退款',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '下单时间',
  `payTime` timestamp NULL DEFAULT NULL COMMENT '支付时间',
  `useTime` timestamp NULL DEFAULT NULL COMMENT '核销时间',
  `refundTime` timestamp NULL DEFAULT NULL COMMENT '退款时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '抢购优惠卷生成的订单' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of voucher_order
-- ----------------------------
INSERT INTO `voucher_order` VALUES (66326763340300291, 1800457759336017922, 14, 1, 1, '2024-06-27 17:41:43', NULL, NULL, NULL, '2024-06-27 17:41:43');

SET FOREIGN_KEY_CHECKS = 1;
