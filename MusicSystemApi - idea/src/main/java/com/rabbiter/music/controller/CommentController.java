package com.rabbiter.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.rabbiter.music.pojo.Comment;
import com.rabbiter.music.service.CommentService;
import com.rabbiter.music.utils.Consts;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

/**
 * 评论控制类
 */
@Api(tags = "评论")
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 添加评论
     */
    @ApiOperation(value = "添加评论")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object addComment(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String userId = request.getParameter("userId");           //用户id
        String songId = request.getParameter("songId");           //歌曲id
        String content = request.getParameter("content").trim();         //评论内容

        //保存到评论的对象中
        Comment comment = new Comment();
        comment.setUserId(Integer.parseInt(userId));
        comment.setContent(content);
        boolean flag = commentService.insert(comment);
        if(flag){   //保存成功
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"评论成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"评论失败");
        return jsonObject;
    }

    /**
     * 修改评论
     */
    @ApiOperation(value = "修改评论")
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Object updateComment(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim();                   //主键
        String userId = request.getParameter("userId").trim();           //用户id
        String songId = request.getParameter("songId").trim();           //歌曲id
        String content = request.getParameter("content").trim();         //评论内容

        //保存到评论的对象中
        Comment comment = new Comment();
        comment.setId(Integer.parseInt(id));
        comment.setUserId(Integer.parseInt(userId));
        if(songId!=null&&songId.equals("")){
            songId = null;
        }else {
            comment.setSongId(Integer.parseInt(songId));
        }
        comment.setContent(content);

        boolean flag = commentService.update(comment);
        if(flag){   //保存成功
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"修改成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"修改失败");
        return jsonObject;
    }

    /**
     * 删除评论
     */
    @ApiOperation(value = "删除评论")
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Object deleteComment(HttpServletRequest request){
        String id = request.getParameter("id").trim();          //主键
        boolean flag = commentService.delete(Integer.parseInt(id));
        return flag;
    }

    /**
     * 根据主键查询整个对象
     */
    @ApiOperation(value = "根据主键查询整个对象")
    @RequestMapping(value = "/selectByPrimaryKey",method = RequestMethod.GET)
    public Object selectByPrimaryKey(HttpServletRequest request){
        String id = request.getParameter("id").trim();          //主键
        return commentService.selectByPrimaryKey(Integer.parseInt(id));
    }

    /**
     * 查询所有评论
     */
    @ApiOperation(value = "查询所有评论")
    @RequestMapping(value = "/allComment",method = RequestMethod.GET)
    public Object allComment(HttpServletRequest request){
        return commentService.allComment();
    }

    /**
     * 查询某个歌曲下的所有评论
     */
    @ApiOperation(value = "查询某个歌曲下的所有评论")
    @RequestMapping(value = "/commentOfSongId",method = RequestMethod.GET)
    public Object commentOfSongId(HttpServletRequest request){
        String songId = request.getParameter("songId");          //歌曲id
        return commentService.commentOfSongId(Integer.parseInt(songId));
    }



}
