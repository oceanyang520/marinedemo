package com.famework.myframedwz.domain.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.famework.myframedwz.domain.common.Page;
import com.famework.myframedwz.domain.model.Assess;
import com.famework.myframedwz.domain.model.Collect;
import com.famework.myframedwz.domain.model.ReturnData;
import com.famework.myframedwz.domain.model.Task;
import com.famework.myframedwz.domain.service.TaskService;
import com.famework.myframedwz.domain.vo.TaskVO;
import com.famework.myframedwz.utils.LatitudeUtils;

@Controller
@RequestMapping(produces = { "text/html;charset=UTF-8" }, value = "task")
public class TaskController {
    private static Logger logger = LoggerFactory.getLogger(TaskController.class);
    @Autowired
    private TaskService taskService;

    /**
     *
     * @Description 查询任务列表
     * @param task
     * @param page
     * @param request
     * @param response
     * @param session
     * @param model
     * @return
     * @author 发哥
     * @date 2015年8月10日 下午5:50:49
     */
    @RequestMapping("/taskMap")
    @ResponseBody
    public String taskMap(Task task,HttpServletRequest request,
                            HttpServletResponse response, HttpSession session, Model model) {
        ReturnData returnData = new ReturnData();
        JSONObject json = new JSONObject();
        try {
            // 参数验证
            if (null == task.getLongitude() || "".equals(task.getLongitude())) {
                json.put("status", false);
                returnData.setMsg("经度不能为空");
                returnData.setReturnCode("1008");
                returnData.setData(json);
                return returnData.toString();
            }
            if (null == task.getLatitude() || "".equals(task.getLatitude())) {
                json.put("status", false);
                returnData.setMsg("纬度不能为空");
                returnData.setReturnCode("1008");
                returnData.setData(json);
                return returnData.toString();
            }
            task.setStatus(0);
            List<TaskVO> list = taskService.selectByStatusMap(task);
            json.put("tasks", list);
            //json.put("count", taskService.selectByStatusCount(task));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            logger.error(e.getMessage());
            returnData.setReturnCode("1009");
            returnData.setMsg("程序错误");
        }
        returnData.setData(json);
        return returnData.toString();
    }

    /**
     * 获取标签列表
     * @param request
     * @param response
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/getAllLabel")
    @ResponseBody
    public String getAllLabel(HttpServletRequest request,
                              HttpServletResponse response, HttpSession session, Model model){
        ReturnData returnData = new ReturnData();
        JSONObject json = new JSONObject();
        try {
            json.put("labelList",taskService.getAllLabel());
        }catch (Exception e){
            // TODO: handle exception
            e.printStackTrace();
            logger.error(e.getMessage());
            returnData.setReturnCode("1009");
            returnData.setMsg("程序错误");
        }
        returnData.setData(json);
        return returnData.toString();
    }
    /**
     * 
    * @Description  获取任务评价信息
    * @param assess
    * @param request
    * @param response
    * @param session
    * @param model
    * @return
    * @author 发哥   
    * @date 2015年9月1日 下午3:47:03
     */
    @RequestMapping("/getAssess")
    @ResponseBody
    public String getAssess(Assess assess,HttpServletRequest request,
            HttpServletResponse response, HttpSession session, Model model){
        ReturnData returnData = new ReturnData();
        JSONObject json = new JSONObject();
        try {
            if(null==assess.getTaskid() || "".equals(assess.getTaskid())){
                json.put("status", false);
                returnData.setMsg("任务ID不能为空");
                returnData.setReturnCode("1008");
                returnData.setData(json);
                return returnData.toString();
            }
            json.put("assess", taskService.getAssessByTaskId(assess.getTaskid()));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            logger.error(e.getMessage());
            returnData.setReturnCode("1009");
            returnData.setMsg("程序错误");
        }
        returnData.setData(json);
        return returnData.toString();
    }
    /**
     * 
    * @Description  添加任务评价
    * @param assess
    * @param request
    * @param response
    * @param session
    * @param model
    * @return
    * @author 发哥   
    * @date 2015年8月31日 下午1:54:07
     */
    @RequestMapping("/insertAssessTask")
    @ResponseBody
    public String insertAssessTask(Assess assess,HttpServletRequest request,
            HttpServletResponse response, HttpSession session, Model model){
        ReturnData returnData = new ReturnData();
        JSONObject json = new JSONObject();
        try {
            if(null==assess.getTaskid() || "".equals(assess.getTaskid())){
                json.put("status", false);
                returnData.setMsg("任务ID不能为空");
                returnData.setReturnCode("1008");
                returnData.setData(json);
                return returnData.toString();
            }
            if(null==assess.getUserid() || "".equals(assess.getUserid())){
                json.put("status", false);
                returnData.setMsg("用户ID不能为空");
                returnData.setReturnCode("1008");
                returnData.setData(json);
                return returnData.toString();
            }
            json.put("status", taskService.insertAssessTask(assess));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            logger.error(e.getMessage());
            returnData.setReturnCode("1009");
            returnData.setMsg("程序错误");
        }
        returnData.setData(json);
        return returnData.toString();
    }
    /**
     * 
     * @Description 查询任务列表
     * @param task
     * @param page
     * @param request
     * @param response
     * @param session
     * @param model
     * @return
     * @author 发哥
     * @date 2015年8月10日 下午5:50:49
     */
    @RequestMapping("/taskFresh")
    @ResponseBody
    public String taskFresh(Task task, Page page, HttpServletRequest request,
            HttpServletResponse response, HttpSession session, Model model) {
        ReturnData returnData = new ReturnData();
        JSONObject json = new JSONObject();
        try {
            // 参数验证
            if (null == task.getLongitude() || "".equals(task.getLongitude())) {
                json.put("status", false);
                returnData.setMsg("经度不能为空");
                returnData.setReturnCode("1008");
                returnData.setData(json);
                return returnData.toString();
            }
            if (null == task.getLatitude() || "".equals(task.getLatitude())) {
                json.put("status", false);
                returnData.setMsg("纬度不能为空");
                returnData.setReturnCode("1008");
                returnData.setData(json);
                return returnData.toString();
            }
            task.setStatus(0);
            task.setPage(page);
            List<TaskVO> list = taskService.selectByStatusPage(task);
            json.put("tasks", list);
            //json.put("count", taskService.selectByStatusCount(task));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            logger.error(e.getMessage());
            returnData.setReturnCode("1009");
            returnData.setMsg("程序错误");
        }
        returnData.setData(json);
        return returnData.toString();
    }

    /**
     * 
     * @Description 发布任务
     * @param task
     * @param page
     * @param request
     * @param response
     * @param session
     * @param model
     * @return
     * @author 发哥
     * @date 2015年8月10日 下午6:41:31
     */
    @RequestMapping("/taskPush")
    @ResponseBody
    public String taskPush(Task task, Collect collect,
            HttpServletRequest request, HttpServletResponse response,
            HttpSession session, Model model) {
        ReturnData returnData = new ReturnData();
        JSONObject json = new JSONObject();
        try {
            // 参数验证
            if (null == task.getTaskname() || "".equals(task.getTaskname())) {
                json.put("status", false);
                returnData.setMsg("任务名称不能为空");
                returnData.setReturnCode("1008");
                returnData.setData(json);
                return returnData.toString();
            }
            if (null == task.getLocation() || "".equals(task.getLocation())) {
                json.put("status", false);
                returnData.setMsg("任务地点不能为空");
                returnData.setReturnCode("1008");
                returnData.setData(json);
                return returnData.toString();
            }
            if (null == task.getPubdate() || "".equals(task.getPubdate())) {
                json.put("status", false);
                returnData.setMsg("任务时间不能为空");
                returnData.setReturnCode("1008");
                returnData.setData(json);
                return returnData.toString();
            }
            if (null == task.getDetail() || "".equals(task.getDetail())) {
                json.put("status", false);
                returnData.setMsg("任务详情不能为空");
                returnData.setReturnCode("1008");
                returnData.setData(json);
                return returnData.toString();
            }
            if (null == task.getPrice() || "".equals(task.getPrice())) {
                json.put("status", false);
                returnData.setMsg("任务价格不能为空");
                returnData.setReturnCode("1008");
                returnData.setData(json);
                return returnData.toString();
            }
            if (null == task.getPopulation() || "".equals(task.getPopulation())) {
                json.put("status", false);
                returnData.setMsg("任务人数不能为空");
                returnData.setReturnCode("1008");
                returnData.setData(json);
                return returnData.toString();
            }
            if (null == task.getPhone() || "".equals(task.getPhone())) {
                json.put("status", false);
                returnData.setMsg("任务联系电话不能为空");
                returnData.setReturnCode("1008");
                returnData.setData(json);
                return returnData.toString();
            }
            if (null == collect.getUserid() || "".equals(collect.getUserid())) {
                json.put("status", false);
                returnData.setMsg("用户ID不能为空");
                returnData.setReturnCode("1008");
                returnData.setData(json);
                return returnData.toString();
            }
            //地址转换成经纬度
            Map<String, String> locJson = LatitudeUtils.getGeocoderLatitude(task.getLocation());  
            task.setLongitude(locJson.get("lng"));
            task.setLatitude(locJson.get("lat"));
            task.setStatus(0);
            taskService.insertSelective(task, collect);
            json.put("status", true);
            json.put("taskid", task.getId());
            json.put("collectid", collect.getId());
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            logger.error(e.getMessage());
            returnData.setReturnCode("1009");
            returnData.setMsg("程序错误");
        }
        returnData.setData(json);
        return returnData.toString();
    }

    /**
     * 
     * @Description 根据状态来查询任务列表0我发布的任务1我收藏的任务2我完成的任务
     * @param collect
     * @param request
     * @param response
     * @param session
     * @param model
     * @return
     * @author 发哥
     * @date 2015年8月11日 下午4:18:50
     */
    @RequestMapping("/taskStatus")
    @ResponseBody
    public String taskType(Collect collect, Page page,
            HttpServletRequest request, HttpServletResponse response,
            HttpSession session, Model model) {
        ReturnData returnData = new ReturnData();
        JSONObject json = new JSONObject();
        try {
            // 参数验证
            if (null == collect.getUserid() || "".equals(collect.getUserid())) {
                json.put("status", false);
                returnData.setMsg("用户ID不能为空");
                returnData.setReturnCode("1008");
                returnData.setData(json);
                return returnData.toString();
            }
            if (null == collect.getStatus() || "".equals(collect.getStatus())) {
                json.put("status", false);
                returnData.setMsg("状态ID不能为空");
                returnData.setReturnCode("1008");
                returnData.setData(json);
                return returnData.toString();
            }
            collect.setPage(page);
            List<TaskVO> list = taskService
                    .selectTasksByUserStatusPage(collect);
            json.put("tasks", list);
            json.put("count", taskService.selectTasksByUserStatusCount(collect));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            logger.error(e.getMessage());
            returnData.setReturnCode("1009");
            returnData.setMsg("程序错误");
        }
        returnData.setData(json);
        return returnData.toString();
    }
    /**
     * 
    * @Description  我收藏的任务/或接受的任务,并更改任务状态
    * @param collect
    * @param request
    * @param response
    * @param session
    * @param model
    * @return
    * @author 发哥   
    * @date 2015年8月11日 下午7:43:45
     */
    @RequestMapping("/collectTask")
    @ResponseBody
    public String collectTask(Collect collect, HttpServletRequest request,
            HttpServletResponse response, HttpSession session, Model model) {
        ReturnData returnData = new ReturnData();
        JSONObject json = new JSONObject();
        try {
            // 参数验证
            if (null == collect.getUserid() || "".equals(collect.getUserid())) {
                json.put("status", false);
                returnData.setMsg("用户ID不能为空");
                returnData.setReturnCode("1008");
                returnData.setData(json);
                return returnData.toString();
            }
            if (null == collect.getTaskid() || "".equals(collect.getTaskid())) {
                json.put("status", false);
                returnData.setMsg("任务ID不能为空");
                returnData.setReturnCode("1008");
                returnData.setData(json);
                return returnData.toString();
            }
            collect.setStatus(2);
            int result = taskService.collectTask(collect);
            if (0 < result) {
                if(1==collect.getStatus()){
                    json.put("status", true);
                }
                if(2==collect.getStatus()){
                    Task task=new Task();
                    task.setId(collect.getTaskid());
                    task.setStatus(1);
                    result=taskService.updateByTaskId(task);
                    if(0<result){
                        json.put("status", true);
                    }
                }
            }else{
                json.put("status", false);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            logger.error(e.getMessage());
            returnData.setReturnCode("1009");
            returnData.setMsg("程序错误");
        }
        returnData.setData(json);
        return returnData.toString();
    }

    /**
     * 
     * @Description 取消收藏任务
     * @param collect
     * @param request
     * @param response
     * @param session
     * @param model
     * @return
     * @author 发哥
     * @date 2015年8月11日 下午7:27:50
     */
    @RequestMapping("/cancelCollect")
    @ResponseBody
    public String cancelCollect(Collect collect, HttpServletRequest request,
            HttpServletResponse response, HttpSession session, Model model) {
        ReturnData returnData = new ReturnData();
        JSONObject json = new JSONObject();
        try {
            // 参数验证
            if (null == collect.getUserid() || "".equals(collect.getUserid())) {
                json.put("status", false);
                returnData.setMsg("用户ID不能为空");
                returnData.setReturnCode("1008");
                returnData.setData(json);
                return returnData.toString();
            }
            if (null == collect.getTaskid() || "".equals(collect.getTaskid())) {
                json.put("status", false);
                returnData.setMsg("任务ID不能为空");
                returnData.setReturnCode("1008");
                returnData.setData(json);
                return returnData.toString();
            }
            int result = taskService.updateByCancelCollect(collect);
            if (0 < result) {
                json.put("status", true);
            }else{
                json.put("status", false);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            logger.error(e.getMessage());
            returnData.setReturnCode("1009");
            returnData.setMsg("程序错误");
        }
        returnData.setData(json);
        return returnData.toString();
    }

    /**
     * 
    * @Description  查询该用户ID是否收藏了该任务
    * @param collect
    * @param request
    * @param response
    * @param session
    * @param model
    * @return
    * @author 发哥   
    * @date 2015年8月14日 上午10:51:55
     */
    @RequestMapping("/isCollectTask")
    @ResponseBody
    public String isCollectTask(Collect collect, HttpServletRequest request,
            HttpServletResponse response, HttpSession session, Model model) {
        ReturnData returnData = new ReturnData();
        JSONObject json = new JSONObject();
        try {
            // 参数验证
            if (null == collect.getUserid() || "".equals(collect.getUserid())) {
                json.put("status", false);
                returnData.setMsg("用户ID不能为空");
                returnData.setReturnCode("1008");
                returnData.setData(json);
                return returnData.toString();
            }
            if (null == collect.getTaskid() || "".equals(collect.getTaskid())) {
                json.put("status", false);
                returnData.setMsg("任务ID不能为空");
                returnData.setReturnCode("1008");
                returnData.setData(json);
                return returnData.toString();
            }
            int result = taskService.selectByIsCollect(collect);
            if (0 < result) {
                json.put("status", true);
            }else{
                json.put("status", false);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            logger.error(e.getMessage());
            returnData.setReturnCode("1009");
            returnData.setMsg("程序错误");
        }
        returnData.setData(json);
        return returnData.toString();
    }
    /**
     * 
    * @Description  更改任务状态
    * @param task
    * @param request
    * @param response
    * @param session
    * @param model
    * @return
    * @author 发哥   
    * @date 2015年9月1日 上午10:59:56
     */
    @RequestMapping("/updateTaskById")
    @ResponseBody
    public String updateTaskById(Task task,HttpServletRequest request,
            HttpServletResponse response, HttpSession session, Model model){
        ReturnData returnData = new ReturnData();
        JSONObject json = new JSONObject();
        // 参数验证
        try {
            if (null == task.getId() || "".equals(task.getId())) {
                json.put("status", false);
                returnData.setMsg("任务ID不能为空");
                returnData.setReturnCode("1008");
                returnData.setData(json);
                return returnData.toString();
            }
            if (null == task.getStatus() || "".equals(task.getStatus())) {
                json.put("status", false);
                returnData.setMsg("任务状态不能为空");
                returnData.setReturnCode("1008");
                returnData.setData(json);
                return returnData.toString();
            }
            int flag=taskService.updateByTaskId(task);
            if(0<flag){
                json.put("status",true);
            }else{
                json.put("status",false);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            logger.error(e.getMessage());
            returnData.setReturnCode("1009");
            returnData.setMsg("程序错误");
        }
        returnData.setData(json);
        return returnData.toString();
    }
    /**
     * 
    * @Description  获取接任务的人信息
    * @param collect
    * @param request
    * @param response
    * @param session
    * @param model
    * @return
    * @author 发哥   
    * @date 2015年9月1日 上午11:21:14
     */
    @RequestMapping("/getTaskDetail")
    @ResponseBody
    public String getTaskDetail(Collect collect,HttpServletRequest request,
            HttpServletResponse response, HttpSession session, Model model){
        ReturnData returnData = new ReturnData();
        JSONObject json = new JSONObject();
        try {
            if(null == collect.getTaskid() || "".equals(collect.getTaskid())){
                json.put("status", false);
                returnData.setMsg("任务ID不能为空");
                returnData.setReturnCode("1008");
                returnData.setData(json);
                return returnData.toString();
            }
            json.put("task", taskService.getTaskDetail(collect));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            logger.error(e.getMessage());
            returnData.setReturnCode("1009");
            returnData.setMsg("程序错误");
        }
        returnData.setData(json);
        return returnData.toString();
    }
}
