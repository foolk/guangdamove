package com.guangdamove.www.controller;

import com.guangdamove.www.entity.TClient;
import com.guangdamove.www.entity.TClientLinkman;
import com.guangdamove.www.mapper.TClientLinkmanMapper;
import com.guangdamove.www.mapper.TClientMapper;
import com.guangdamove.www.util.Power;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Intian
 * @create 2021-07-27 17:49
 */
@RestController
@CrossOrigin
public class ClientController {
    @Autowired
    private TClientMapper tClientMapper;


    @Autowired
    private TClientLinkmanMapper tClientLinkmanMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    /*
    客户列表查询接口	GMIT	核心	客户管理	core003	queryGrpList
    准客户列表查询接口	GMIT	核心	客户管理	core002	queryPreGrpList*/
    @RequestMapping("queryPreGrpList")
    public List<TClient> queryPreGrpList(int id){
        String key = "tclients_" + id;
        ValueOperations<String, List<TClient>> operations = redisTemplate.opsForValue();
        if(redisTemplate.hasKey(key)){
            return operations.get(key);
        }else{
            List<TClient> tClients = tClientMapper.tClientList(id);
            operations.set(key, tClients, 5, TimeUnit.MINUTES);
            return tClients;
        }
    }
    /*客户详情查询接口	GMIT	核心	客户管理	core004	queryGrpInfo*/
    @RequestMapping("queryPreGrpList1")
    public Object queryPreGrpList1(Integer id){
        String key = "tclient_" + id;
        ValueOperations<String, TClient> operations = redisTemplate.opsForValue();
        if(redisTemplate.hasKey(key)){
            return operations.get(key);
        }else{
            TClient tClient = tClientMapper.tClientList1(id);
            operations.set(key, tClient, 5, TimeUnit.MINUTES);
            return tClient;
        }
    }

    /*准客户删除*/
    @RequestMapping(value = "deleteClient",method = RequestMethod.DELETE)
    public Power deleteClient(Integer id){
        //查询改用户是哪个分类的
        int i = Integer.parseInt(tClientMapper.tClientList1(id).getStarLevel());
        System.out.println(i);
        String key = "tclients_" + i;
        //删除数据库中数据
        tClientMapper.deleteByPrimaryKey(id);
        tClientLinkmanMapper.deleteByCid(id);
        //获取redis
        ValueOperations<String, List<TClient>> operations = redisTemplate.opsForValue();
        //如果redis库中有的话，重储key，value值
        if(redisTemplate.hasKey(key)){
            operations.set(key, tClientMapper.tClientList(i), 5, TimeUnit.MINUTES);
        }
        return new Power("删除成功",true,1);
    }
    /*添加本地客户*/
    @RequestMapping(value = "insertClient",method = RequestMethod.POST)
    public Power insertClient(TClient tClient){
        tClient.setStarLevel("3");
        tClientMapper.insert(tClient);
        Integer id = tClientMapper.tClientListByCname(tClient.getCname()).getId();
        for (TClientLinkman clientLinkman : tClient.getTclientLinkmen()) {
            clientLinkman.setCid(id);
            clientLinkman.setFlag(1);
            tClientLinkmanMapper.insert(clientLinkman);
        }
        String key = "tclients_" + 3;
        //获取redis
        ValueOperations<String, List<TClient>> operations = redisTemplate.opsForValue();
        //如果redis库中有的话，重储key，value值
        if(redisTemplate.hasKey(key)){
            operations.set(key, tClientMapper.tClientList(3), 5, TimeUnit.MINUTES);
        }
        return new Power("添加成功",true,id);
    }

    /*准客户维护接口	GMIT	核心	客户管理	core001	manaPreGrpInfo*/
    /*修改客户*/
    @RequestMapping(value = "updateClient",method = RequestMethod.POST)
    public Power updateClient(TClient tClient){
        System.out.println(tClient);
        tClientMapper.updateByPrimaryKey(tClient);
        int iid = 0;
        for (TClientLinkman clientLinkman : tClient.getTclientLinkmen()) {
            if(iid==0){
                tClientLinkmanMapper.deleteByPrimaryKey(clientLinkman.getId());
                iid = clientLinkman.getCid();
            }
            clientLinkman.setCid(iid);
            tClientLinkmanMapper.insert(clientLinkman);
        }
        String key = "tclients_" + tClient.getStarLevel();
        //获取redis
        ValueOperations<String, List<TClient>> operations = redisTemplate.opsForValue();
        //如果redis库中有的话，重储key，value值
        operations.set(key, tClientMapper.tClientList(Integer.parseInt(tClient.getStarLevel())), 5, TimeUnit.MINUTES);

        return new Power("修改成功",true,1);
    }

    /*搜索*/
    @RequestMapping("seacher")
    public Object seacherNamePhoneNumberIDCard(Power p){
        if(p.getFlag().length()<11){
            //客户名称
            try{
                if (Integer.parseInt(p.getFlag())>0){
                    System.out.println(12);
                    return tClientMapper.tClientListByPhone(p.getFlag());
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println(1);
            return tClientMapper.tClientListByName(p.getFlag());
        }else if(p.getFlag().length()==11){
            //手机号
            System.out.println(2);
            return tClientMapper.tClientListByPhone(p.getFlag());
        }else{
            //证件号码
            System.out.println(3);
            return tClientMapper.tClientListByIdCard(p.getFlag());
        }
    }

    /*搜索*/
    @RequestMapping("seacher2")
    public Object seacherNamePhoneNumberIDCard2(Power p){
        if(p.getFlag().length()<11){
            //客户名称
            try{
                if (Integer.parseInt(p.getFlag())>0){
                    System.out.println(12);
                    return tClientMapper.tClientListByPhone2(p.getFlag());
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println(1);
            return tClientMapper.tClientListByName2(p.getFlag());
        }else if(p.getFlag().length()==11){
            //手机号
            System.out.println(2);
            return tClientMapper.tClientListByPhone2(p.getFlag());
        }else{
            //证件号码
            System.out.println(3);
            System.out.println(p.getFlag());
            List<TClient> tClients = tClientMapper.tClientListByIdCard(p.getFlag());
            for (TClient tClient : tClients) {
                System.out.println(tClient);
            }

            return tClientMapper.tClientListByIdCard(p.getFlag());
        }
    }

    /*删除一个联系人*/
    @RequestMapping(value = "deleteClientLinkMan",method = RequestMethod.DELETE)
    public Power deleteClientLinkMan(int id){
        tClientLinkmanMapper.deleteByPrimaryKey(id);
        return new Power("删除成功",true,1);
    }



}
