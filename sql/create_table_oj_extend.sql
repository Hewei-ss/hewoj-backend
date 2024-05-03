/*
 Navicat Premium Data Transfer

 Source Server         : hewei
 Source Server Type    : MySQL
 Source Server Version : 80024
 Source Host           : localhost:3306
 Source Schema         : hewoj

 Target Server Type    : MySQL
 Target Server Version : 80024
 File Encoding         : 65001

 Date: 24/04/2024 15:00:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for post
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post`  (
                         `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
                         `title` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '标题',
                         `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '内容',
                         `tags` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '标签列表（json 数组）',
                         `thumbNum` int NOT NULL DEFAULT 0 COMMENT '点赞数',
                         `favourNum` int NOT NULL DEFAULT 0 COMMENT '收藏数',
                         `userId` bigint NOT NULL COMMENT '创建用户 id',
                         `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                         `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                         `isDelete` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除',
                         PRIMARY KEY (`id`) USING BTREE,
                         INDEX `idx_userId`(`userId` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '帖子' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of post
-- ----------------------------

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '帖子收藏' ROW_FORMAT = Dynamic;


DROP TABLE IF EXISTS `comment_answer`;
CREATE TABLE `comment_answer`(
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    `userId` bigint NOT NULL COMMENT '用户id',
    `answerId` bigint NOT NULL COMMENT '题解id',
    `commentContent` TEXT NOT NULL COMMENT '评论内容',
    `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `isDelete` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除',
    PRIMARY KEY (`id`) USING BTREE
)ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '题解评论表' ROW_FORMAT = Dynamic;
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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '帖子点赞' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of post_thumb
-- ----------------------------

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
) ENGINE = InnoDB AUTO_INCREMENT = 1782304631221833729 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '题目' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO `question` VALUES (1, 'A+B', '题目内容', '[\"简单\"]', '暴力', 0, 0, '[{\"input\":\"1 1\",\"output\":\"2\"}]', '{\"timeLimit\":1000,\"memoryLimit\":1000,\"stackLimit\":1000}', 0, 0, 1744901815402741761, '2024-01-13 20:43:06', '2024-04-15 11:46:07', 0);
INSERT INTO `question` VALUES (1746151836467027969, 'A+C', '', '[]', '', 0, 0, NULL, NULL, 0, 0, 1744901815402741761, '2024-01-13 20:46:54', '2024-03-29 10:37:25', 0);
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
                                    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '题目题解表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of question_answer
-- ----------------------------

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
) ENGINE = InnoDB AUTO_INCREMENT = 1782222206227582978 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '题目提交' ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 1779843967073005570 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1744901815402741761, 'yupi', 'b0dd3697a192885d7c055db46155b26a', NULL, NULL, 'hew', NULL, NULL, 'user', '2024-01-10 09:59:46', '2024-04-23 09:13:02', 0);
INSERT INTO `user` VALUES (1779768810514046978, 'hewei', 'b0dd3697a192885d7c055db46155b26a', NULL, NULL, 'hewei', NULL, NULL, 'user', '2024-04-15 15:08:45', '2024-04-15 20:09:40', 0);
INSERT INTO `user` VALUES (1779769618563489793, 'test', 'b0dd3697a192885d7c055db46155b26a', NULL, NULL, NULL, NULL, NULL, 'user', '2024-04-15 15:11:58', '2024-04-15 15:11:58', 0);
INSERT INTO `user` VALUES (1779770810165243905, 'test2', 'b0dd3697a192885d7c055db46155b26a', NULL, NULL, NULL, NULL, NULL, 'user', '2024-04-15 15:16:42', '2024-04-15 15:16:42', 0);
INSERT INTO `user` VALUES (1779772088211615746, 'test3', 'b0dd3697a192885d7c055db46155b26a', NULL, NULL, NULL, NULL, NULL, 'user', '2024-04-15 15:21:47', '2024-04-15 15:21:47', 0);
INSERT INTO `user` VALUES (1779843967073005570, 'test4', 'b0dd3697a192885d7c055db46155b26a', NULL, NULL, NULL, NULL, NULL, 'user', '2024-04-15 20:07:24', '2024-04-15 20:07:24', 0);

SET FOREIGN_KEY_CHECKS = 1;
