package com.denghb.dbhelper;

import java.text.MessageFormat;
import java.util.List;

import com.denghb.dbhelper.paging.Paging;
import com.denghb.dbhelper.paging.PagingResult;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.denghb.dbhelper.domain.User;
import com.denghb.dbhelper.domain.UserFilter;
import com.denghb.dbhelper.utils.DbHelperUtils;

@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class AppTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private DbHelper db;

    @Test
    public void insert() {
        for (int i = 0; i < 100000; i++) {

            User user = new User();
            user.setAge(18);
            user.setEmail("insert@qq.com");
            user.setName("张三" + i);

            boolean res = db.insert(user);
            Assert.assertTrue(res);
            System.out.println("insert:" + user);
        }
    }

    @Test
    public void update() {
        User user = new User();
        user.setId(1);
        user.setEmail("update@qq.com");

        boolean res = db.updateById(user);
        Assert.assertTrue(res);
        System.out.println("update:" + user);
    }

    @Test
    public void queryById() {
        User user = db.queryById(User.class, 2);
        Assert.assertNotNull(user);
        System.out.println("queryById:" + user);
    }

    @Test
    public void list() {
        List<User> list = db.list("select * from user where name = ?", User.class, "张三");

        Assert.assertNotNull(list);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    @Test
    public void listArray() {
        List<Integer> list = db.list("select id from user where name = ?", Integer.class, "张三");
        Assert.assertNotNull(list);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    @Test
    public void listPage() {
        StringBuffer sql = new StringBuffer();
        // sql.append("select id,name,mobile from user");
        // sql.append("select * from user");
        sql.append("select u1.* from user u1 left join user u2 on u2.id = u1.id left join user u3 on u3.id = u1.id where 1=1 and u1.id > ? ");

        Paging paging = new UserFilter();
        paging.setSort(true);
        paging.setPage(10L);
        paging.getParams().add(1);
        PagingResult<User> result = db.list(sql, User.class, paging);

        List<User> list = result.getList();
        Assert.assertNotNull(list);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    @Test
    public void deleteById() {
        boolean res = db.deleteById(User.class, 1);
        Assert.assertTrue(res);
    }

    @Test
    public void updates() {

        long[] accountIds = new long[]{1, 2};
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < accountIds.length; i++) {
            if (0 != i) {
                sb.append(",");
            }
            sb.append(accountIds[i]);

        }
        String sql = MessageFormat.format(
                "update admin.account_access set status = {0},updated_by = ? where account_id in ({1}) and status = {2}", 1, sb.toString(), 0);
        int s = db.execute(sql, 0L);
        System.out.println(s);
    }

}
