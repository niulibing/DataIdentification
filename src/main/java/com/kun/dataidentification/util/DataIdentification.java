package com.kun.dataidentification.util;

import cn.hutool.core.util.IdcardUtil;
import com.kun.dataidentification.exception.InvalidIDCardException;
import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>该类为数据识别工具类，主要包括身份证、手机号、邮箱、车牌等的校验，以及身份证相关数据获取(通过身份证号获取相关数据的时候，方法内部已经对身份证有效性做了检查，使用者无需单独校验身份证是否有效)</>
 *
 * @author niulibing
 * @since 4.0.1
 */
public final class DataIdentification {


    /**
     * 校验身份证
     *
     * @param idCard 身份证号
     * @return 校验通过返回true，校验不通过返回false
     */
    public static boolean checkIdentityCard(String idCard) throws IllegalArgumentException {
        if (StringUtils.isEmpty(idCard)) {
            throw new IllegalArgumentException("身份证号码不能为空，请检查.");
        }
        return IdcardUtil.isValidCard(idCard);
    }

    /**
     * 根据身份证号码获取生日
     *
     * @param idCard 身份证号
     * @return 生日
     * @throws IllegalArgumentException 当传入的身份证号码为空时，抛出非法参数异常
     * @throws InvalidIDCardException   当身份证号码无效，抛出身份证无效异常
     */
    public static String getBirthByIdCard(String idCard) throws IllegalArgumentException, InvalidIDCardException {

        if (checkIdentityCard(idCard)) {
            return IdcardUtil.getBirthByIdCard(idCard);
        } else {
            throw new InvalidIDCardException("身份证号码无效，请输入正确的身份证号码.");
        }
    }

    /**
     * 根据身份证号码获取省份
     *
     * @param idCard 身份证号
     * @return 省份
     * @throws IllegalArgumentException 当传入的身份证号码为空时，抛出非法参数异常
     * @throws InvalidIDCardException   当身份证号码无效，抛出身份证无效异常
     */
    public static String getProvinceByIdCard(String idCard) throws IllegalArgumentException, InvalidIDCardException {

        if (checkIdentityCard(idCard)) {
            return IdcardUtil.getProvinceByIdCard(idCard);
        } else {
            throw new InvalidIDCardException("身份证号码无效，请输入正确的身份证号码.");
        }
    }

    /**
     * 将15位的身份证转换成18位
     *
     * @param idCard 15位身份证号码
     * @return 转换后的身份证号
     * @throws IllegalArgumentException 当传入的身份证号码为空或者非15位身份证，抛出非法参数异常
     * @throws InvalidIDCardException   当身份证号码无效，抛出身份证无效异常
     */
    public static String convert15To18(String idCard) throws IllegalArgumentException, InvalidIDCardException {

        if (checkIdentityCard(idCard)) {
            String id18 = IdcardUtil.convert15To18(idCard);
            if (StringUtils.isEmpty(id18)) {
                throw new IllegalArgumentException("请输入15位的身份证号码");
            }
            return id18;
        } else {
            throw new InvalidIDCardException("身份证号码无效，请输入正确的身份证号码.");
        }
    }

    /**
     * 获取年龄
     *
     * @param idCard 18位身份证
     * @return 年龄
     * @throws IllegalArgumentException 当传入的身份证号码为空时，抛出非法参数异常
     * @throws InvalidIDCardException   当身份证号码无效，抛出身份证无效异常
     */
    public static int getAgeByIdCard(String idCard) throws IllegalArgumentException, InvalidIDCardException {

        if (checkIdentityCard(idCard)) {
            return IdcardUtil.getAgeByIdCard(idCard);
        } else {
            throw new InvalidIDCardException("身份证号码无效，请输入正确的身份证号码.");
        }
    }

    /**
     * 根据身份证号码获取性别
     *
     * @param idCard 身份证号码
     * @return 性别 1:男，0：女
     * @throws IllegalArgumentException 当传入的身份证号码为空时，抛出非法参数异常
     * @throws InvalidIDCardException   当身份证号码无效，抛出身份证无效异常
     */
    public static int getGenderByIdCard(String idCard) throws IllegalArgumentException, InvalidIDCardException {

        if (checkIdentityCard(idCard)) {
            return IdcardUtil.getGenderByIdCard(idCard);
        } else {
            throw new InvalidIDCardException("身份证号码无效，请输入正确的身份证号码.");
        }
    }


    /**
     * 校验手机号
     *
     * @param phoneNumber 需要校验的手机号码
     * @return 校验通过：true,校验未通过：false
     * @throws IllegalArgumentException 当传入的手机号码为空或者null，抛出该异常
     */
    public static boolean checkPhoneNumber(String phoneNumber) throws IllegalArgumentException {

        if (StringUtils.isEmpty(phoneNumber)) {
            throw new IllegalArgumentException("手机号码为空，请检查.");
        }
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(phoneNumber);
        return m.matches();
    }

    /**
     * 校验邮箱
     *
     * @param email 需要校验的邮箱地址
     * @return 校验通过：true 未通过：false
     * @throws IllegalArgumentException 当传入的邮箱为空或者为null，抛出该异常。
     */
    public static boolean checkEmail(String email) {

        if (StringUtils.isEmpty(email)) {
            throw new IllegalArgumentException("非法参数，邮箱号不能为null");
        }
        String regex = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern compile = Pattern.compile(regex);
        Matcher matcher = compile.matcher(email);
        return matcher.matches();
    }

    /**
     * 校验车牌号码
     *
     * @param plateNumber 需要校验的车牌号码
     * @return 校验通过：true 校验未通过：false
     * @throws IllegalArgumentException 当传入的车牌号为null，抛出该异常
     */
    public static boolean checkPlateNumber(String plateNumber) {

        if (StringUtils.isEmpty(plateNumber)) {
            throw new IllegalArgumentException("非法参数，车牌号不能为null");
        }
        plateNumber = plateNumber.trim().replaceAll(" ", "");
        String regex = "^([京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[a-zA-Z](([DF]((?![IO])[a-zA-Z0-9](?![IO]))[0-9]{4})|([0-9]{5}[DF]))|[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1})$";
        Pattern compile = Pattern.compile(regex);
        Matcher matcher = compile.matcher(plateNumber);
        return matcher.matches();
    }

}

