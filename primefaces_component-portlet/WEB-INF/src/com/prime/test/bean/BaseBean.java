package com.prime.test.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liferay.faces.portal.context.LiferayFacesContext;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Address;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.Phone;
import com.liferay.portal.model.User;
import com.liferay.portal.service.AddressLocalServiceUtil;
import com.liferay.portal.service.PhoneLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;


public class BaseBean implements Serializable{

	private static final long serialVersionUID = 7687838716516495314L;

	protected LiferayFacesContext liferayFacesContext;
	
	private static final Logger log = LoggerFactory.getLogger(BaseBean.class);
	
	public BaseBean() {
		
		liferayFacesContext = LiferayFacesContext.getInstance();
	}
	
	/**
	 * LÃ¡ÂºÂ¥y portlet id
	 * @return
	 */
	public String getPortletId() {
		FacesContext context = FacesContext.getCurrentInstance();
		String portletId = context.getExternalContext().encodeNamespace("");
		
		return portletId;
	}
	
	/**
	 * LÃ¡ÂºÂ¥y id cÃ¡Â»Â§a mÃ¡Â»â„¢t instance cÃ¡Â»Â§a portlet
	 * @param context
	 * @return
	 */
	public String getStringPlId() {
		String plId;
		plId = "" + liferayFacesContext.getThemeDisplay().getPlid();
		
		return plId;
	}
	/**
	 * @author  nlphuong
	 * @purpose Kiá»ƒm tra ngÆ°á»�i dÃ¹ng Ä‘Ã£ Ä‘Äƒng nháº­p hay chÆ°a
	 * @date    24-12-2013 :: 10:14:14
	 * @return
	 */
	public boolean hasSignedIn(){
		return liferayFacesContext.getThemeDisplay().isSignedIn();
	}
	/**
	 * LÃ¡ÂºÂ¥y unique identifier cÃ¡Â»Â§a portlet
	 * @return
	 */
	public long getPlid() {
		return liferayFacesContext.getPlid();
	}
	
	public String getPortletDisplayId() {
		return liferayFacesContext.getThemeDisplay().getPortletDisplay().getId();
	}
	
	/**
	 * LÃ¡ÂºÂ¥y company id
	 * @return
	 */
	public long getCompanyId() {
		long companyid = liferayFacesContext.getCompanyId();
		
		return companyid;
	}
	
	/**
	 * LÃ¡ÂºÂ¥y company id cÃ¡Â»Â§a portal chÃƒÂ­nh
	 * @return
	 */
	public long getDefaultCompanyId() {
		return PortalUtil.getDefaultCompanyId();
	}
	
	/**
	 * LÃ¡ÂºÂ¥y user id cÃ¡Â»Â§a user Ã„â€˜ang Ã„â€˜Ã„Æ’ng nhÃ¡ÂºÂ­p
	 * @return
	 */
	public long getUserId() {
		long userid = liferayFacesContext.getUserId();
		
		return userid;
	}
	
	/**
	 * LÃ¡ÂºÂ¥y thÃƒÂ´ng tin User Ã„â€˜ang Ã„â€˜Ã„Æ’ng nhÃ¡ÂºÂ­p
	 * @return
	 */
	public User getUser() {
		return liferayFacesContext.getUser();
	}
	
	public String getUserFullName(){
		User u = getUser();
		return u.getFirstName() + " " + u.getMiddleName() + " " + u.getLastName();
	}
	
	/**
	 * LÃ¡ÂºÂ¥y id cÃ¡Â»Â§a organization chÃƒÂ­nh gÃ¡ÂºÂ§n nhÃ¡ÂºÂ¥t cÃ¡Â»Â§a ngÃ†Â°Ã¡Â»ï¿½i dÃƒÂ¹ng Ã„â€˜ang Ã„â€˜Ã„Æ’ng nhÃ¡ÂºÂ­p
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 */
	public long getIdPrimOrganization() throws PortalException, SystemException{
		List<Organization> userOrg = liferayFacesContext.getUser().getOrganizations();
		Organization tempOrg;
		boolean primOrg = false;
		long organizationid = -1000;
		
		for (Organization org : userOrg) {
			primOrg = Boolean.parseBoolean(org.getExpandoBridge().getAttribute("PrimOrg").toString());
			if (primOrg) {
				organizationid = org.getOrganizationId();
				break;
			} else {
				tempOrg = org.getParentOrganization();
				while(tempOrg != null){
					primOrg = Boolean.parseBoolean(tempOrg.getExpandoBridge().getAttribute("PrimOrg").toString());
					if(primOrg){
						organizationid = tempOrg.getOrganizationId();
						break;
					}else{
						tempOrg = tempOrg.getParentOrganization();
					}
				}
			}
		}
		
		return organizationid;
	}

	/**
	 * LÃ¡ÂºÂ¥y id hÃƒÂ¬nh Ã„â€˜Ã¡ÂºÂ¡i diÃ¡Â»â€¡n
	 * @param user
	 * @return
	 */
	public long getUserPortrailId() {
		long portrailId = 0;
		try{
			portrailId = getUser().getPortraitId();
		}catch(Exception e){
			log.error("", e);
		}
		
		return portrailId;
	}
	
	/**
	 * lÃ¡ÂºÂ¥y url hÃƒÂ¬nh Ã„â€˜Ã¡ÂºÂ¡i diÃ¡Â»â€¡n
	 * @param user object User
	 * @return avatar image url
	 */
	public String getUserAvatarUrl(User user){
		String result = "";
		ThemeDisplay themeDisplay = liferayFacesContext.getThemeDisplay();
		try{
			result = themeDisplay.getPathImage() + "/user_portrait?img_id=" + getUserPortrailId();
		}catch(Exception e){
			//no image found
			log.error("", e);
		}
		return result;
	}
	
	/**
	 * LÃ¡ÂºÂ¥y tÃƒÂªn phÃƒÂ¡p lÃƒÂ½ (legal name) cÃ¡Â»Â§a portal
	 * @return
	 */
	public String getCompanyLegalName() {
		String legalName = "";
		try {
			legalName = liferayFacesContext.getThemeDisplay().getCompany().getAccount().getLegalName();
		} catch (PortalException e) {
			log.error("", e);
		} catch (SystemException e) {
			log.error("", e);
		}
		
		return legalName;
	}
	
	/**
	 * LÃ¡ÂºÂ¥y sÃ¡Â»â€˜ Ã„â€˜iÃ¡Â»â€¡n thoÃ¡ÂºÂ¡i Ã„â€˜Ã¡ÂºÂ§u tiÃƒÂªn cÃ¡Â»Â§a portal
	 * @return
	 */
	public String getCompanyFirstPhoneNumber() {
		String phoneNumber = "";
		try {
			List<Phone> phoneList = PhoneLocalServiceUtil.getPhones();
			for (Phone objPhone : phoneList) {
				if (objPhone.isPrimary() == true) {
					phoneNumber = objPhone.getExtension() + " " + objPhone.getNumber();
				}
			}
		} catch (SystemException e) {
			log.error("", e);
		}
		
		return phoneNumber;
	}
	
	/**
	 * LÃ¡ÂºÂ¥y Ã„â€˜Ã¡Â»â€¹a chÃ¡Â»â€° (address) chÃƒÂ­nh (primary) cÃ¡Â»Â§a portal
	 * @return
	 */
	public String getCompanyFirstAddress() {
		String address = "";
		List<Address> addressList;
		try {
			addressList = AddressLocalServiceUtil.getAddresses();
			for (Address objAddress : addressList) {
				if (objAddress.isPrimary() == true) {
					address = objAddress.getStreet1();
					break;
				}
			}
		} catch (SystemException e) {
			log.error("", e);
		}
		
		return address;
	}
	
	/**
	 * LÃ¡ÂºÂ¥y tÃƒÂªn Ã„â€˜Ã†Â¡n vÃ¡Â»â€¹ (legal name trong Portal Setting)
	 * @return
	 */
	public String getTenDonVi() {
		return getCompanyLegalName();
	}
	
	/**
	 * LÃ¡ÂºÂ¥y Ã„â€˜Ã¡Â»â€¹a chÃ¡Â»â€° cÃ¡Â»Â§a Ã„â€˜Ã†Â¡n vÃ¡Â»â€¹ (Street1 cÃ¡Â»Â§a Address chÃƒÂ­nh trong Portal Setting)
	 * @return
	 */
	public String getDiaChiDonVi() {
		return getCompanyFirstAddress();
	}
	
	/**
	 * LÃ¡ÂºÂ¥y sÃ¡Â»â€˜ Ã„â€˜iÃ¡Â»â€¡n thoÃ¡ÂºÂ¡i cÃ¡Â»Â§a Ã„â€˜Ã†Â¡n vÃ¡Â»â€¹ (Ext vÃƒÂ  Number cÃ¡Â»Â§a Phone Number chÃƒÂ­nh trong Portal Setting)
	 * @return
	 */
	public String getSoDienThoaiDonVi() {
		return getCompanyFirstPhoneNumber();
	}
	
}
