package test;

import com.duan.ssh.entity.Goods;
import com.duan.ssh.entity.Order;
import com.duan.ssh.entity.Shoppingcart;
import com.duan.ssh.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate4.HibernateTemplate;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @ProjectName: javaEE_ssh
 * @Package: test
 * @ClassName: SpringTest
 * @Author: duan
 * @Description: Spring测试
 * @Date: 2019-05-28 22:48
 * @Version: 1.0
 */
public class SpringTest {

    @Test
    public void springTest() throws Exception {
        String xmlPath = "classpath:spring/applicationContext.xml";
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
//        UserService userService = (UserService) applicationContext.getBean("userServiceImpl");
//        userService.Hello();
//        User user = new User();
//        user.setUsername("admin1");
//        user.setPassword("1111111");
//        user.setDefaultPhone("44444");
//
//        Order order = new Order();
//        order.setCreatetime(new Timestamp(38333333));
//        order.setSumprice(444);
//        order.setUser(user);
//        Order order1 = new Order();
//        order1.setCreatetime(new Timestamp(33333533));
//        order1.setSumprice(55);
//        order1.setUser(user);
//        Order order2 = new Order();
//        order2.setCreatetime(new Timestamp(33533333));
//        order2.setSumprice(66);
//        order2.setUser(user);
//
//        user.getSetOrder().add(order);
//        user.getSetOrder().add(order1);
//        user.getSetOrder().add(order2);
//
//        int result = userService.registerUser(user);
//        System.out.println("result:" + result);

        HibernateTemplate hibernateTemplate = (HibernateTemplate) applicationContext.getBean("hibernateTemplate");
        Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();

//        Goods goods = new Goods();
//        goods.setCreatetime(new Timestamp(33533333));
//        goods.setName("商品");
//        goods.setCount(100);
//        goods.setPrice(100);
//
//        Goods goods1 = new Goods();
//        goods1.setCreatetime(new Timestamp(33233333));
//        goods1.setName("商品1");
//        goods1.setCount(101);
//        goods1.setPrice(101);
//
//        Goods goods2 = new Goods();
//        goods2.setCreatetime(new Timestamp(33233333));
//        goods2.setName("商品2");
//        goods2.setCount(102);
//        goods2.setPrice(102);

        Goods good1 = session.get(Goods.class, 1);
        Goods good2 = session.get(Goods.class, 2);

        Order order = new Order();
        order.setSumprice(2121);
        order.setCreatetime(new Timestamp(new Date().getTime()));

        User user = session.get(User.class, 10);
//        User user = new User();
//        user.setUsername("duan");
//        user.setPassword("root");
//        user.setDefaultPhone("9999999");

//        user.getSetOrder().add(order);
//        order.setUser(user);

//        List<Goods> goodsList = new ArrayList<>();
//        goodsList.add(goods);
//        goodsList.add(goods);
//        goodsList.add(goods2);
//
//        OrderGoods orderGoods;
//        int i = 1;
//        for (Goods good : goodsList) {
//            orderGoods = new OrderGoods();
//            orderGoods.setOrder(order);
//            orderGoods.setGoods(good);
//            orderGoods.setGoodsCount(i);
//            order.getSetOrderGoods().add(orderGoods);
//            i++;
//        }

        Shoppingcart shoppingcart = new Shoppingcart();
        shoppingcart.setCreatetime(new Timestamp(33533333));
        shoppingcart.setUser(user);
        shoppingcart.setGoods(good2);
        shoppingcart.setGoodsCount(10);
        shoppingcart.setStatus(0);

        session.save(shoppingcart);
        tx.commit();


    }
}
