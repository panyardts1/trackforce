package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.revature.dao.HomeDao;
import com.revature.dao.HomeDaoImpl;
import com.revature.entity.TfAssociate;
import com.revature.model.ClientInfo;

@Path("/")
public class HomeResource {

	@GET
	@Path("info")
	@Produces({ MediaType.APPLICATION_JSON })
	public ClientInfo getMappedAndUnmappedInfo() {
		List<TfAssociate> associates = new ArrayList<>();
		return countAssociatesBasedOnStatus(associates);
	}
	
	@GET
	@Path("{statusid}")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<TfAssociate> getAssociatesByStatus(@PathParam("statusid") int statusid) {
		HomeDao dao = new HomeDaoImpl();
		List<TfAssociate> associates = dao.getAllTfAssociates();
		return associatesListByStatus(associates, statusid);
	}

	private ClientInfo countAssociatesBasedOnStatus(List<TfAssociate> associates) {
		ClientInfo clientInfo = new ClientInfo();
		for (TfAssociate associate : associates) {
			switch (associate.getTfMarketingStatus().getTfMarketingStatusId().intValue()) {
			case 1:
				clientInfo.setTrainingMapped(clientInfo.getTrainingMapped() + 1);
				break;
			case 2:
				clientInfo.setReservedMapped(clientInfo.getReservedMapped() + 1);
				break;
			case 3:
				clientInfo.setSelectedMapped(clientInfo.getSelectedMapped() + 1);
				break;
			case 4:
				clientInfo.setConfirmedMapped(clientInfo.getConfirmedMapped() + 1);
				break;
			case 5:
				clientInfo.setDeployedMapped(clientInfo.getDeployedMapped() + 1);
				break;
			case 6:
				clientInfo.setTrainingUnmapped(clientInfo.getTrainingUnmapped() + 1);
				break;
			case 7:
				clientInfo.setOpenUnmapped(clientInfo.getOpenUnmapped() + 1);
				break;
			case 8:
				clientInfo.setSelectedUnmapped(clientInfo.getSelectedUnmapped() + 1);
				break;
			case 9:
				clientInfo.setConfirmedUnmapped(clientInfo.getConfirmedUnmapped() + 1);
				break;
			case 10:
				clientInfo.setDeployedUnmapped(clientInfo.getDeployedUnmapped() + 1);
				break;
			}
		}

		clientInfo = new ClientInfo("My test", 2, 4, 24, 6, 5, 15, 61, 14, 13, 4);
		return clientInfo;
	}
	
	/**
	 * This method takes a list of TfAssociates and a desired marketing status ID,
	 * and filters the list to give back a list of only TfAssociates who are listed under that ID.
	 * @param allAssociates the list of all TfAssociates from the database
	 * @param id the marketing status ID we want to filter by
	 * @return a list of TfAssociates filtered by the marketing status id
	 */
	private List<TfAssociate> associatesListByStatus(List<TfAssociate> allAssociates, int id){
		List<TfAssociate> assoc = new ArrayList<>();
		for(TfAssociate associate : allAssociates) {
			if (associate.getTfMarketingStatus().getTfMarketingStatusId().intValue() == id){
				assoc.add(associate);
			}
		}
		return assoc;
	}

}
