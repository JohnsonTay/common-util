package com.cly.common.service;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.internal.metadata.facets.Validatable;

import javax.validation.constraints.NotNull;
import java.util.Set;

/*
 * 修订记录:
 * qzhanbo@yiji.com 2013-01-22 10:43 创建
 *
 * qzhanbo@yiji.com 2014-01-22 10:43 增加checkWithGroup
 *
 */

/**
 * 提供jsr303校验能力
 * @author qzhanbo@yiji.com
 */
public abstract class OrderBase extends TransactionContext implements Order,Validatable {
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 统一流水id
	 */
	@NotNull
	@Length(min = 35, max = 35)
	private String gid;
	
	public String getGid() {
		return gid;
	}
	
	/**
	 *
	 * @param gid 为了保证gid全局唯一,请使用 {@link com.cly.common.id.GID#newGID()}
	 */
	public void setGid(String gid) {
		this.gid = gid;
	}
	
	/**
	 * 通过jsr303规范的注解来校验参数 <br/>
	 * 建议使用 checkWithGroup 来进行判断，在不同的场景使用group来区分；<br/>
	 *
	 * <p/>
	 * 例如：<br/>
	 *	<pre>
	 * @ NotNull(groups={ADD.class,Modify.class})
	 *
	 *  //新增的时候调用：
	 * order.checkWithGroup(ADD.class)
	 *
	 * //修改的时候调用：
	 * order.checkWithGroup(Modify.class)
	 *
	 *
	 * @see com.yjf.common.service.Order#check()
	 * @see OrderBase#checkWithGroup(Class[])
	 */
	@Override
	public void check() {
		Set<ConstraintViolation<OrderBase>> constraintViolations = YJFValidatorFactory.INSTANCE.getValidator()
			.validate(this);
		validate(constraintViolations);
	}
	
	/**
	 * 通过jsr303规范的注解来校验参数
	 * @param groups 校验groups
	 */
	public void checkWithGroup(Class<?>... groups) {
		Set<ConstraintViolation<OrderBase>> constraintViolations = YJFValidatorFactory.INSTANCE.getValidator()
			.validate(this, groups);
		validate(constraintViolations);
	}
	
	protected <T> void validate(Set<ConstraintViolation<T>> constraintViolations) {
		OrderCheckException exception = null;
		if (constraintViolations != null && !constraintViolations.isEmpty()) {
			exception = new OrderCheckException();
			for (ConstraintViolation<T> constraintViolation : constraintViolations) {
				exception.addError(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
			}
		}
		if (exception != null) {
			throw exception;
		}
	}
	
	@Override
	public String toString() {
		return ToString.toString(this);
	}
}
