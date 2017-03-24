package com.rolmex.android.oa.activity;

import com.rolmex.android.emalltone.R;
import com.rolmex.android.oa.MainApp;

import java.util.ArrayList;
import java.util.List;

public class MenuBuilder extends AbsMenuBuilder {
    @Override
    protected List<MenuEntity> buildAllMenu() {
        List<MenuEntity> menuList = new ArrayList<>();
        MenuEntity dealerCenter = new MenuEntity("经销商中心", R.drawable.ic_menu_dealer);
        menuList.add(dealerCenter);
        dealerCenter.addSubMenu("服务列表");
        dealerCenter.addSubMenu("销售列表");
        dealerCenter.addSubMenu("我的报单");
        dealerCenter.addSubMenu("重消列表");
        dealerCenter.addSubMenu("五周最新业绩");
        dealerCenter.addSubMenu("指麦优品服务列表");

        MenuEntity pwdManage = new MenuEntity("密码管理", R.drawable.ic_menu_pwd);
        menuList.add(pwdManage);
        pwdManage.addSubMenu("修改一级密码");
        pwdManage.addSubMenu("修改二级密码");

        if (MainApp.getInstance().getUser().isChrIsShop()) {
            MenuEntity shopCenter = new MenuEntity("店中心", R.drawable.ic_menu_shop);
            menuList.add(shopCenter);
            shopCenter.addSubMenu("店销售列表");
            shopCenter.addSubMenu("新伙伴加入");
            shopCenter.addSubMenu("新建升级单");
            shopCenter.addSubMenu("新建补单升级");
            shopCenter.addSubMenu("新建重复消费单");
            shopCenter.addSubMenu("店钱包");
            shopCenter.addSubMenu("店交易记录");
            shopCenter.addSubMenu("专卖店转账");
            shopCenter.addSubMenu("查看全部单据");
            shopCenter.addSubMenu("店钱包转入电商");
            shopCenter.addSubMenu("资格认证");
            shopCenter.addSubMenu("订单签收");
            shopCenter.addSubMenu("新伙伴加入(奖券)");
            shopCenter.addSubMenu("新建重复消费单(奖券)");
            shopCenter.addSubMenu("专卖店奖券转账");
            shopCenter.addSubMenu("补报重复消费单");
        }else{
            MenuEntity personCenter = new MenuEntity("销售中心","(个人)", R.drawable.ic_menu_shop);
            menuList.add(personCenter);
            String hideName= "(个人)";
            personCenter.addSubMenu("销售列表",hideName);
            personCenter.addSubMenu("新伙伴加入",hideName);
            personCenter.addSubMenu("新建升级单",hideName);
            personCenter.addSubMenu("新建补单升级",hideName);
            personCenter.addSubMenu("新建重复消费单",hideName);
            personCenter.addSubMenu("钱包",hideName);
            personCenter.addSubMenu("交易记录",hideName);
            personCenter.addSubMenu("转账申请",hideName);
            personCenter.addSubMenu("查看全部单据",hideName);
            personCenter.addSubMenu("钱包转入电商",hideName);
            personCenter.addSubMenu("订单签收",hideName);
        }

        MenuEntity accountManage = new MenuEntity("账簿管理", R.drawable.ic_menu_account);
        menuList.add(accountManage);
        accountManage.addSubMenu("我的钱包");
        accountManage.addSubMenu("提现申请");
        accountManage.addSubMenu("转账申请");
        accountManage.addSubMenu("提现记录");
        accountManage.addSubMenu("转入电商");
        accountManage.addSubMenu("查看交易记录");
        accountManage.addSubMenu("提成历史明细");
        accountManage.addSubMenu("费用缴纳");
        accountManage.addSubMenu("缴费记录");
        accountManage.addSubMenu("奖券转账");
        accountManage.addSubMenu("奖券转入电商");

        menuList.add(new MenuEntity("产品介绍", R.drawable.ic_menu_product));

        MenuEntity userCenter = new MenuEntity("个人中心", R.drawable.ic_menu_user);
        menuList.add(userCenter);
        userCenter.addSubMenu("资料维护");
        userCenter.addSubMenu("资格认证确认");
        userCenter.addSubMenu("销售员申请书");
        if (MainApp.getInstance().getUser().isSenior()) {
            userCenter.addSubMenu("属地登记");
        }
        userCenter.addSubMenu("重消活动抽奖");
        userCenter.addSubMenu("兑换奖品");
        userCenter.addSubMenu("查询奖品兑换单");
        userCenter.addSubMenu("抽奖及兑换记录");
        userCenter.addSubMenu("邀请码");

        MenuEntity infoCenter = new MenuEntity("信息中心", R.drawable.ic_menu_info);
        menuList.add(infoCenter);
        infoCenter.addSubMenu("公司公告");
        infoCenter.addSubMenu("资料下载");

        menuList.add(new MenuEntity("我要订货", "(个人)", R.drawable.ic_menu_order));
        menuList.add(new MenuEntity("我要订货", R.drawable.ic_menu_order));

        menuList.add(new MenuEntity("查询订货单", R.drawable.ic_menu_query));
        menuList.add(new MenuEntity("查询订货单","(个人)", R.drawable.ic_menu_query));

        MenuEntity doubleConsumption =new MenuEntity("重消专区",R.drawable.ic_menu_double);
        doubleConsumption.addSubMenu("新建重复消费订单");
        doubleConsumption.addSubMenu("查看重复消费订单");
        doubleConsumption.addSubMenu("查看重复消费订单","(个人)");
        menuList.add(doubleConsumption);

        MenuEntity distributorCenter=new MenuEntity("代理商中心",R.drawable.ic_menu_account);
        distributorCenter.addSubMenu("缴费明细");
        menuList.add(distributorCenter);

        MenuEntity oaOnLine=new MenuEntity("在线办公",R.drawable.ic_menu_online);
        oaOnLine.addSubMenu("密码初始化申请");
        oaOnLine.addSubMenu("密码初始化记录");
        oaOnLine.addSubMenu("更正姓名申请");
        oaOnLine.addSubMenu("更正姓名记录");
        oaOnLine.addSubMenu("更正身份证申请");
        oaOnLine.addSubMenu("更正身份证记录");
        oaOnLine.addSubMenu("资格注销申请");
        oaOnLine.addSubMenu("资格注销记录");
        oaOnLine.addSubMenu("直系亲属资格替换申请");
        oaOnLine.addSubMenu("直系亲属资格替换记录");
        oaOnLine.addSubMenu("更正手机号申请");
        oaOnLine.addSubMenu("更正手机号记录");
        oaOnLine.addSubMenu("申请开通订货及转账权限");
        oaOnLine.addSubMenu("申请开通订货及转账权限记录");
        menuList.add(oaOnLine);

        MenuEntity bindWX=new MenuEntity("绑定微信",R.drawable.ic_menu_bindwx);
        menuList.add(bindWX);
        return menuList;
    }
}
