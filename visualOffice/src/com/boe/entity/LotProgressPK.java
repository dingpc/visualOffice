package com.boe.entity;

import java.io.Serializable;

public class LotProgressPK implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long lotId;

	private Long manageId;

	public Long getLotId() {
		return lotId;
	}

	public void setLotId(Long lotId) {
		this.lotId = lotId;
	}

	public Long getManageId() {
		return manageId;
	}

	public void setManageId(Long manageId) {
		this.manageId = manageId;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof LotProgressPK) {
			LotProgressPK pk = (LotProgressPK) obj;
			if (this.lotId.equals(pk.lotId) && this.manageId.equals(pk.manageId)) {
				return true;
			}
		}
		return false;
	}
}
