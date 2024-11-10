package service.custom.Impl;

import entity.OrderItem;
import entity.ReturnItem;
import entity.Returns;
import repository.DaoFactory;
import repository.custom.ReturnDao;
import service.custom.ReturnService;
import util.DaoType;

import java.util.List;

public class ReturnServiceImpl implements ReturnService {
    @Override
    public boolean returnOrder(Returns returns, List<ReturnItem> returnItems) {

        for (ReturnItem returnItem : returnItems){
            returns.addReturnsItem(returnItem);
        }

        ReturnDao returnDao= DaoFactory.getInstance().getDaoType(DaoType.RETURN);
        return returnDao.save(returns);

    }
}
