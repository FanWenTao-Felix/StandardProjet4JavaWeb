### Notes

+ MyBatis模糊查询
```xml
<if test="abcName!=null">
	and abc_name like CONCAT('%',#{abcName},'%' )
</if>
```
```java
if(abcName != null){
     params.put("abcName", abcName.replaceAll("'", "\\\\'").replaceAll("_", "\\\\_").replaceAll("%", "\\\\%"));
}
```

+ mybatis数据批量插入
<http://blog.csdn.net/tylai520/article/details/6763197>
```xml
<insert id="insertbatch" parameterType="java.util.List">  
    <selectKey keyProperty="fetchTime" order="BEFORE"  
        resultType="java.lang.String">  
        SELECT CURRENT_TIMESTAMP()  
    </selectKey>  
    insert into kangaiduoyaodian ( depart1, depart2, abc_name,  
    generic_name, img, abc_specification, unit,  
    approval_certificate, manufacturer, marketPrice, abcPrice,  
    website, fetch_time, abcdesc ) values  
    <foreach collection="list" item="item" index="index"  
        separator=",">  
        ( #{item.depart1}, #{item.depart2}, #{item.abcName},  
        #{item.genericName}, #{item.img},  
        #{item.abcSpecification}, #{item.unit},  
        #{item.approvalCertificate}, #{item.manufacturer},  
        #{item.marketprice}, #{item.abcprice}, #{item.website},  
        #{fetchTime}, #{item.abcdesc} )  
    </foreach>  
</insert>
```
在批处理中，我发现有几个需要注意的问题

1、主键的自动获取，在insert中添加useGeneratedKeys=”true” keyProperty=”id”这两个属性无效，
并且或中断数据插入，如果id是数据库自增的话，可以什么都不写，在插入的语句中去除主键属性，还有就是利用
```xml
<selectKey keyProperty="id" order="BEFORE"  
        resultType="java.lang.Integer">  
        SELECT LAST_INSERT_ID()  
</selectKey>  
```

注意：<selectKey > 标签在insert下只能存在一个；批处理的时候不适合使用<selectKey >,主键自增最好，或者指定
2，插入时间的获取如上面所示，我用的是mysql，只要是mysql函数都可以拿来使用，插入时间和主键都是mysql函数中的一个。

+ mybatis调用其他mapper文件的方法，作为返回结果的字段
```xml
<resultMap id="ListabcsByHourEntityMap"
		type="com.abc.kxw.api.virtualabc.model.ListabcsByHourEntity">
		<result column="abc_id" property="virtualabcId" jdbcType="INTEGER" />
		<result column="abc_id" property="abcId" jdbcType="INTEGER" />
		<result column="property" property="property" jdbcType="INTEGER" />
		<result column="custom_main_image" property="customMainImage"
			jdbcType="VARCHAR" />
		<result column="mobile_show_from" property="mobileShowFrom"
			jdbcType="INTEGER" />
		<result column="mobile_show_to" property="mobileShowTo"
			jdbcType="INTEGER" />
		<association column="{abcId=abc_id,salePlatform=sale_platform,abcId=abc_id}"
			property="superScriptList"
			select="com.abc.kxw.superscript.mapper.abcabcSuperscriptMapper.listabcabcSuperscript" />
		<association column="{abcId=abc_id,salePlatform=sale_platform,abcId=abc_id}"
			property="labelList"
			select="com.abc.kxw.label.mapper.abcabcLabelMapper.listabcabcLabel" />
</resultMap>

<resultMap id="CompleteResultMap"
		type="com.abc.kxw.abc.entity.Appsabcabcs">
		<id column="id" property="id" jdbcType="INTEGER" />
		<result column="abc_id" property="abcId" jdbcType="INTEGER" />
		<result column="abc_id" property="abcId" jdbcType="INTEGER" />
		<result column="sale_platform" property="salePlatform"
			jdbcType="VARCHAR" />
		<result column="property" property="property" jdbcType="INTEGER" />
		<result column="warehouse" property="warehouse" jdbcType="VARCHAR" />
		<result column="custom_main_image" property="customMainImage"
			jdbcType="VARCHAR" />
		<result column="create_time" property="createTime" jdbcType="TIMESTAMP" />
		<result column="is_deleted" property="isDeleted" jdbcType="INTEGER" />
		<result column="abc_name" property="abcName" jdbcType="VARCHAR" />
		<result column="weight_value_for_buy_quantity" property="weightValueForBuyQuantity"
			jdbcType="INTEGER" />
		<result column="minimum_limiting_buy_quantity" property="minimumLimitingBuyQuantity"
			jdbcType="INTEGER" />
		<result column="maxmum_limiting_buy_quantity" property="maxmumLimitingBuyQuantity"
			jdbcType="INTEGER" />
		<result column="superscript_count" property="superscriptCount"
			jdbcType="INTEGER" />
		<result column="label_count" property="labelCount"
			jdbcType="INTEGER" />
		<association column="{abcId=abc_id,salePlatform=sale_platform,abcId=abc_id}"
			property="superScriptList"
			select="com.abc.kxw.superscript.mapper.abcabcSuperscriptMapper.listabcabcSuperscript" />
		<association column="{abcId=abc_id,salePlatform=sale_platform,abcId=abc_id}"
			property="labelList"
			select="com.abc.kxw.label.mapper.abcabcLabelMapper.listabcabcLabel" />
</resultMap>

<select id="selectAll" resultMap="CompleteResultMap">
		select
		<include refid="Base_Column_List" />
		from t_apps_abc_abcs
		where is_deleted=0
</select>
<select id="listabcabcSuperscript" resultMap="BaseResultMap"
		parameterType="map">
		SELECT
		t1.id,t1.superscript_id,t1.seq,t1.create_time,t2.title,t2.icon,t2.description,t2.position
		FROM t_apps_abc_abc_superscript AS t1 JOIN t_apps_superscript
		AS t2
		ON
		t1.superscript_id=t2.id AND
		t1.abc_id=#{abcId} AND
		t1.abc_id=#{abcId} AND
		t1.sale_platform=#{salePlatform}
		ORDER BY
		t1.seq ASC
</select>
```
```java
com.abc.kxw.abc.entity.Appsabcabcs
    private List<abcabcSuperscript> superScriptList;
    private List<abcabcLabel> labelList;
```

+ Mybatis的bean含有list类型的属性
```xml
<resultMap id="ChannelInfoMap" type="com.abc.kxw.channel.entity.ChannelInfoEntity" >
    <id column="id" property="id" jdbcType="INTEGER" />
    <result column="web_id" property="webId" jdbcType="INTEGER" />
    <result column="type" property="type" jdbcType="INTEGER" />
    <result column="is_mobile" property="isMobile" jdbcType="TINYINT" />
    <result column="is_deleted" property="isDeleted" jdbcType="TINYINT" />
    <result column="channel_name" property="channelName" jdbcType="VARCHAR" />
    <result column="remark" property="remark" jdbcType="VARCHAR" />
    <result column="create_time" property="createTime" jdbcType="TIMESTAMP" />
    <result column="update_time" property="updateTime" jdbcType="TIMESTAMP" />   
	   <collection property="typeList" resultMap="channelTypeMap" />
  </resultMap>
 
  <select id="getAll" resultMap="ChannelInfoMap">
    select cc.*,t.id as t_id,t.channel_id,t.type_id 
    from (select c.id, c.web_id, c.is_mobile,c.is_deleted,c.channel_name,c.create_time              
          from t_channel_info as c where is_deleted=0) cc   
    left join t_channel_type as t on t.channel_id = cc.id
  </select>
```
<pre>
mysql> select cc.*,t.id as t_id,t.channel_id,t.type_id
    from (select c.id, c.web_id, c.is_mobile,c.is_deleted,c.channel_name,c.create_time
          from t_channel_info as c where is_deleted=0) cc
    left join t_channel_type as t on t.channel_id = cc.id;
+-----+--------+-----------+------------+--------------+---------------------+------+------------+---------+
| id  | web_id | is_mobile | is_deleted || create_time         | t_id | channel_id | type_id |
+-----+--------+-----------+------------+--------------+---------------------+------+------------+---------+
|   1 |      0 |         0 |          0 |         | 2014-08-23 00:53:55 |  536 |          1 |       9 |
|   1 |      0 |         0 |          0 |          | 2014-08-23 00:53:55 |  537 |          1 |      10 |
|   1 |      0 |         0 |          0 |         | 2014-08-23 00:53:55 |  538 |          1 |      11 |
|   1 |      0 |         0 |          0 | 首         | 2014-08-23 00:53:55 |  539 |          1 |      12 |
|   2 |     10 |         0 |          0 | 美       | 2014-08-23 00:53:55 |  504 |          2 |      10 |
|   3 |     11 |         0 |          0 | 母       | 2014-08-23 00:53:55 |  505 |          3 |      11 |
|   4 |     12 |         0 |          0 |          | 2014-08-23 00:53:55 |  506 |          4 |      12 |
|   5 |      0 |         0 |          0 | 即     | 2014-08-23 00:53:55 |  507 |          5 |       9 |
|   5 |      0 |         0 |          0 | 即     | 2014-08-23 00:53:55 |  508 |          5 |      10 |
|   5 |      0 |         0 |          0 | 即     | 2014-08-23 00:53:55 |  509 |          5 |      11 |
|   5 |      0 |         0 |          0 | 即     | 2014-08-23 00:53:55 |  510 |          5 |      12 |
|   6 |      0 |         0 |          0 | 预         | 2014-08-23 00:53:55 |  511 |          6 |       9 |
|   6 |      0 |         0 |          0 | 预        | 2014-08-23 00:53:55 |  512 |          6 |      10 |
|   6 |      0 |         0 |          0 | 预         | 2014-08-23 00:53:55 |  513 |          6 |      11 |
|   6 |      0 |         0 |          0 | 预        | 2014-08-23 00:53:55 |  514 |          6 |      12 |
|   7 |      0 |         0 |          0 | 搜         | 2014-08-23 00:53:55 |  515 |          7 |       9 |
|   7 |      0 |         0 |          0 | 搜         | 2014-08-23 00:53:55 |  516 |          7 |      10 |
|   7 |      0 |         0 |          0 | 搜         | 2014-08-23 00:53:55 |  517 |          7 |      11 |
|   7 |      0 |         0 |          0 | 搜         | 2014-08-23 00:53:55 |  518 |          7 |      12 |
|   8 |      0 |         0 |          0 | 服         | 2014-08-23 00:53:55 |  541 |          8 |       9 |
| 105 |      0 |         1 |          0 | test         | 2014-09-04 13:57:00 |  535 |        105 |       4 |
+-----+--------+-----------+------------+--------------+---------------------+------+------------+---------+
21 rows in set
</pre>

```java
package com.abc.kxw.channel.entity;
import java.util.Date;
import java.util.List;
public class ChannelInfoEntity implements java.io.Serializable {
	public static final int IS_MOBILE_YES = 1;
	
	public static final int IS_MOBILE_NO = 0;
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * primary key(auto increment)
	 **/
	private Integer id;
	/**
	 * web channel id
	 **/
	private Integer webId;
	/**
	 * channel type:1:common channel,2:flexible channel
	 **/
	private Integer type;
	/**
	 * is only mobile channel
	 **/
	private Integer isMobile;
	/**
	 * is deleted
	 **/
	private Integer isDeleted;
	/**
	 * channel name
	 **/
	private String channelName;
	/**
	 * channel remark
	 **/
	private String remark;
	/**
	 * create time 
	 **/
	private Date createTime;
	
	private List<ChannelTypeEntity> typeList;
```
关键：`<collection property="typeList" resultMap="channelTypeMap" />`

---

MyBatis 增强工具 pndao - 帮你自动写 SQL

构建微服务：如何优雅的使用mybatis:<https://my.oschina.net/u/2928967/blog/782629>


