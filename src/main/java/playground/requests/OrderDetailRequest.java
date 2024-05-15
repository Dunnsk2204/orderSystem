package playground.requests;

import java.util.List;

public class OrderDetailRequest {
	
	List<DetailRequest> detailRequestlist;

	public List<DetailRequest> getDetailRequestlist() {
		return detailRequestlist;
	}

	public void setDetailRequestlist(List<DetailRequest> detailRequestlist) {
		this.detailRequestlist = detailRequestlist;
	}

}
