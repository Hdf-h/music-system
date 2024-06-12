package com.rabbiter.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.rabbiter.music.pojo.Attention;
import com.rabbiter.music.pojo.Collect;
import com.rabbiter.music.service.AdminService;
import com.rabbiter.music.service.AttentionService;
import com.rabbiter.music.service.CollectService;
import com.rabbiter.music.utils.Consts;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 收藏控制类
 */
@RestController
@RequestMapping("/attention")
@Api(tags = "关注")
public class AttentionController {

    @Autowired
    private AttentionService attentionService;

    /**
     * 添加收藏
     */
    @ApiOperation(value = "添加关注")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object addAttention(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String userId = request.getParameter("userId");           //用户id
        String useredId = request.getParameter("useredId");           //被关注用户id
        if(useredId==null||useredId.equals("")){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"关注列表为空");
            return jsonObject;
        }
        if(attentionService.existUseredId(Integer.parseInt(userId),Integer.parseInt(useredId))){
            jsonObject.put(Consts.CODE,2);
            jsonObject.put(Consts.MSG,"已关注");
            return jsonObject;
        }

        //保存到收藏的对象中
        Attention Attention=new Attention();
        Attention.setUserId(Integer.parseInt(userId));
        Attention.setuseredId(Integer.parseInt(useredId));

        boolean flag = attentionService.insert(Attention);
        if(flag){   //保存成功
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"关注成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"关注失败");
        return jsonObject;
    }

    /**
     * 删除收藏
     */
    @ApiOperation(value = "取消关注")
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Object deleteAttention(HttpServletRequest request){
        String userId = request.getParameter("userId");           //用户id
        String useredId = request.getParameter("useredId");           //被关注用户id
        boolean flag = attentionService.deleteByUserIduseredId(Integer.parseInt(userId),Integer.parseInt(useredId));
        return flag;
    }

    /**
     * 查询所有收藏
     */
    @ApiOperation(value = "查看所有关注")
    @RequestMapping(value = "/allAtteintion",method = RequestMethod.GET)
    public Object allCollect(HttpServletRequest request){
        return attentionService.allAttention();
    }

    /**
     * 查询某个用户的收藏列表
     */
    @ApiOperation(value = "用户的关注列表")
    @RequestMapping(value = "/attentionOfUserId",method = RequestMethod.GET)
    public Object attentionOfUserId(HttpServletRequest request){
        String userId = request.getParameter("userId");          //用户id
        return attentionService.attentionOfUserId(Integer.parseInt(userId));
    }

}
