package com.example.lenovo.demo29.bean;

import java.util.List;

/**
 * 作者：周子强
 * 邮箱：1670375515@qq.com
 * 时间：2019/5/15
 * 项目工作空间：Demo29
 */
public class BanMiParticularsBean {

    /**
     * code : 0
     * desc :
     * result : {"share":{"shareTitle":"我是JoJo，看看我的私藏旅行线路！","shareContent":"作为全球深度游旅行玩家，我的生活和旅行路线绝对精彩！","shareImage":"http://cdn.banmi.com/banmiapp/rahdna/1528685160318_380c455a73d17d6bea4a6db74d62fb80.jpg","shareURL":"http://banmi.com/app2017/banmi.html?id=54"},"banmi":{"id":54,"name":"JoJo","location":"苏州","occupation":"全球深度游旅行玩家","introduction":"你好，我是旅行玩家JoJo。从高中开始就在澳洲西部的珀斯留学，大学转到了东部的墨尔本。在将近7年的澳洲学习生活中，每个周末只要有空，我就会和朋友去周边旅行。其中我最喜欢的就是在西澳自驾游，不仅景美，西澳人的朴实热情更是让我感觉亲切友好，内心温暖。所以假如你想要一个放松心情、放慢脚步的旅行，我一定会首先推荐你来西澳。\n\n直到现在，我对旅行的上瘾热度也一直未减，一年有将近一半的时间我都在世界各地深度旅行，目前已经流浪过十几个国家，除了澳洲，待得最久的地方就是韩国了。我跑遍了梨泰院、江南、圣水洞等等我喜欢的街区，掘地三尺，挖出了不少我喜欢的地方。尤其因为我喜欢住在当地人家里，和他们一起生活、交谈，这样可以让我最快速地体验到最地道的本地文化，发现最受当地人欢迎的地方。所以跟着我深度游，可以了解每座城市不一样的魅力哦。","following":1631,"photo":"http://cdn.banmi.com/banmiapp/rahdna/1528685160318_380c455a73d17d6bea4a6db74d62fb80.jpg","isFollowed":false,"routesCount":2},"activities":[{"id":198,"content":"京都三日匆忙就结束了，遇到对的人每天都是儿童节！","audioURL":"","audioLength":0,"images":["http://cdn.banmi.com/banmiapp/rahdna/1528687645655_017ac0c99763774956d6539de09f77bb.jpg","http://cdn.banmi.com/banmiapp/rahdna/1528687668169_d0faf96c50fa3c8a85ffa7e39592b773.jpg","http://cdn.banmi.com/banmiapp/rahdna/1528687672881_7b758f169b9b2d665e826222944f74ad.jpg","http://cdn.banmi.com/banmiapp/rahdna/1528687676506_1a1ade61808bd9c6a4bfb0850779ad67.jpg"],"firstImageWidth":0,"firstImageHeight":0,"likeCount":1,"replyCount":0,"isLiked":false,"date":"2018-06-11 11:29"},{"id":192,"content":"跟着我一起，带你走进最正宗的西澳世界！","audioURL":"","audioLength":0,"images":["http://cdn.banmi.com/banmiapp/rahdna/1521189079955_6bdbdf233f26cf41460141534fd18d16.jpg","http://cdn.banmi.com/banmiapp/rahdna/1521189093580_5a7fe91afa8557cba6940a1aa827134e.jpg"],"firstImageWidth":0,"firstImageHeight":0,"likeCount":18,"replyCount":0,"isLiked":false,"date":"2018-03-16 16:33"}],"page":1,"limit":20,"count":2}
     */

    private int code;
    private String desc;
    private ResultBean result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * share : {"shareTitle":"我是JoJo，看看我的私藏旅行线路！","shareContent":"作为全球深度游旅行玩家，我的生活和旅行路线绝对精彩！","shareImage":"http://cdn.banmi.com/banmiapp/rahdna/1528685160318_380c455a73d17d6bea4a6db74d62fb80.jpg","shareURL":"http://banmi.com/app2017/banmi.html?id=54"}
         * banmi : {"id":54,"name":"JoJo","location":"苏州","occupation":"全球深度游旅行玩家","introduction":"你好，我是旅行玩家JoJo。从高中开始就在澳洲西部的珀斯留学，大学转到了东部的墨尔本。在将近7年的澳洲学习生活中，每个周末只要有空，我就会和朋友去周边旅行。其中我最喜欢的就是在西澳自驾游，不仅景美，西澳人的朴实热情更是让我感觉亲切友好，内心温暖。所以假如你想要一个放松心情、放慢脚步的旅行，我一定会首先推荐你来西澳。\n\n直到现在，我对旅行的上瘾热度也一直未减，一年有将近一半的时间我都在世界各地深度旅行，目前已经流浪过十几个国家，除了澳洲，待得最久的地方就是韩国了。我跑遍了梨泰院、江南、圣水洞等等我喜欢的街区，掘地三尺，挖出了不少我喜欢的地方。尤其因为我喜欢住在当地人家里，和他们一起生活、交谈，这样可以让我最快速地体验到最地道的本地文化，发现最受当地人欢迎的地方。所以跟着我深度游，可以了解每座城市不一样的魅力哦。","following":1631,"photo":"http://cdn.banmi.com/banmiapp/rahdna/1528685160318_380c455a73d17d6bea4a6db74d62fb80.jpg","isFollowed":false,"routesCount":2}
         * activities : [{"id":198,"content":"京都三日匆忙就结束了，遇到对的人每天都是儿童节！","audioURL":"","audioLength":0,"images":["http://cdn.banmi.com/banmiapp/rahdna/1528687645655_017ac0c99763774956d6539de09f77bb.jpg","http://cdn.banmi.com/banmiapp/rahdna/1528687668169_d0faf96c50fa3c8a85ffa7e39592b773.jpg","http://cdn.banmi.com/banmiapp/rahdna/1528687672881_7b758f169b9b2d665e826222944f74ad.jpg","http://cdn.banmi.com/banmiapp/rahdna/1528687676506_1a1ade61808bd9c6a4bfb0850779ad67.jpg"],"firstImageWidth":0,"firstImageHeight":0,"likeCount":1,"replyCount":0,"isLiked":false,"date":"2018-06-11 11:29"},{"id":192,"content":"跟着我一起，带你走进最正宗的西澳世界！","audioURL":"","audioLength":0,"images":["http://cdn.banmi.com/banmiapp/rahdna/1521189079955_6bdbdf233f26cf41460141534fd18d16.jpg","http://cdn.banmi.com/banmiapp/rahdna/1521189093580_5a7fe91afa8557cba6940a1aa827134e.jpg"],"firstImageWidth":0,"firstImageHeight":0,"likeCount":18,"replyCount":0,"isLiked":false,"date":"2018-03-16 16:33"}]
         * page : 1
         * limit : 20
         * count : 2
         */

        private ShareBean share;
        private BanmiBean banmi;
        private int page;
        private int limit;
        private int count;
        private List<ActivitiesBean> activities;

        public ShareBean getShare() {
            return share;
        }

        public void setShare(ShareBean share) {
            this.share = share;
        }

        public BanmiBean getBanmi() {
            return banmi;
        }

        public void setBanmi(BanmiBean banmi) {
            this.banmi = banmi;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<ActivitiesBean> getActivities() {
            return activities;
        }

        public void setActivities(List<ActivitiesBean> activities) {
            this.activities = activities;
        }

        public static class ShareBean {
            /**
             * shareTitle : 我是JoJo，看看我的私藏旅行线路！
             * shareContent : 作为全球深度游旅行玩家，我的生活和旅行路线绝对精彩！
             * shareImage : http://cdn.banmi.com/banmiapp/rahdna/1528685160318_380c455a73d17d6bea4a6db74d62fb80.jpg
             * shareURL : http://banmi.com/app2017/banmi.html?id=54
             */

            private String shareTitle;
            private String shareContent;
            private String shareImage;
            private String shareURL;

            public String getShareTitle() {
                return shareTitle;
            }

            public void setShareTitle(String shareTitle) {
                this.shareTitle = shareTitle;
            }

            public String getShareContent() {
                return shareContent;
            }

            public void setShareContent(String shareContent) {
                this.shareContent = shareContent;
            }

            public String getShareImage() {
                return shareImage;
            }

            public void setShareImage(String shareImage) {
                this.shareImage = shareImage;
            }

            public String getShareURL() {
                return shareURL;
            }

            public void setShareURL(String shareURL) {
                this.shareURL = shareURL;
            }
        }

        public static class BanmiBean {
            /**
             * id : 54
             * name : JoJo
             * location : 苏州
             * occupation : 全球深度游旅行玩家
             * introduction : 你好，我是旅行玩家JoJo。从高中开始就在澳洲西部的珀斯留学，大学转到了东部的墨尔本。在将近7年的澳洲学习生活中，每个周末只要有空，我就会和朋友去周边旅行。其中我最喜欢的就是在西澳自驾游，不仅景美，西澳人的朴实热情更是让我感觉亲切友好，内心温暖。所以假如你想要一个放松心情、放慢脚步的旅行，我一定会首先推荐你来西澳。

             直到现在，我对旅行的上瘾热度也一直未减，一年有将近一半的时间我都在世界各地深度旅行，目前已经流浪过十几个国家，除了澳洲，待得最久的地方就是韩国了。我跑遍了梨泰院、江南、圣水洞等等我喜欢的街区，掘地三尺，挖出了不少我喜欢的地方。尤其因为我喜欢住在当地人家里，和他们一起生活、交谈，这样可以让我最快速地体验到最地道的本地文化，发现最受当地人欢迎的地方。所以跟着我深度游，可以了解每座城市不一样的魅力哦。
             * following : 1631
             * photo : http://cdn.banmi.com/banmiapp/rahdna/1528685160318_380c455a73d17d6bea4a6db74d62fb80.jpg
             * isFollowed : false
             * routesCount : 2
             */

            private int id;
            private String name;
            private String location;
            private String occupation;
            private String introduction;
            private int following;
            private String photo;
            private boolean isFollowed;
            private int routesCount;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getOccupation() {
                return occupation;
            }

            public void setOccupation(String occupation) {
                this.occupation = occupation;
            }

            public String getIntroduction() {
                return introduction;
            }

            public void setIntroduction(String introduction) {
                this.introduction = introduction;
            }

            public int getFollowing() {
                return following;
            }

            public void setFollowing(int following) {
                this.following = following;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            public boolean isIsFollowed() {
                return isFollowed;
            }

            public void setIsFollowed(boolean isFollowed) {
                this.isFollowed = isFollowed;
            }

            public int getRoutesCount() {
                return routesCount;
            }

            public void setRoutesCount(int routesCount) {
                this.routesCount = routesCount;
            }
        }

        public static class ActivitiesBean {
            /**
             * id : 198
             * content : 京都三日匆忙就结束了，遇到对的人每天都是儿童节！
             * audioURL :
             * audioLength : 0
             * images : ["http://cdn.banmi.com/banmiapp/rahdna/1528687645655_017ac0c99763774956d6539de09f77bb.jpg","http://cdn.banmi.com/banmiapp/rahdna/1528687668169_d0faf96c50fa3c8a85ffa7e39592b773.jpg","http://cdn.banmi.com/banmiapp/rahdna/1528687672881_7b758f169b9b2d665e826222944f74ad.jpg","http://cdn.banmi.com/banmiapp/rahdna/1528687676506_1a1ade61808bd9c6a4bfb0850779ad67.jpg"]
             * firstImageWidth : 0
             * firstImageHeight : 0
             * likeCount : 1
             * replyCount : 0
             * isLiked : false
             * date : 2018-06-11 11:29
             */

            private int id;
            private String content;
            private String audioURL;
            private int audioLength;
            private int firstImageWidth;
            private int firstImageHeight;
            private int likeCount;
            private int replyCount;
            private boolean isLiked;
            private String date;
            private List<String> images;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getAudioURL() {
                return audioURL;
            }

            public void setAudioURL(String audioURL) {
                this.audioURL = audioURL;
            }

            public int getAudioLength() {
                return audioLength;
            }

            public void setAudioLength(int audioLength) {
                this.audioLength = audioLength;
            }

            public int getFirstImageWidth() {
                return firstImageWidth;
            }

            public void setFirstImageWidth(int firstImageWidth) {
                this.firstImageWidth = firstImageWidth;
            }

            public int getFirstImageHeight() {
                return firstImageHeight;
            }

            public void setFirstImageHeight(int firstImageHeight) {
                this.firstImageHeight = firstImageHeight;
            }

            public int getLikeCount() {
                return likeCount;
            }

            public void setLikeCount(int likeCount) {
                this.likeCount = likeCount;
            }

            public int getReplyCount() {
                return replyCount;
            }

            public void setReplyCount(int replyCount) {
                this.replyCount = replyCount;
            }

            public boolean isIsLiked() {
                return isLiked;
            }

            public void setIsLiked(boolean isLiked) {
                this.isLiked = isLiked;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public List<String> getImages() {
                return images;
            }

            public void setImages(List<String> images) {
                this.images = images;
            }
        }
    }
}
