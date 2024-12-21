package dao;

import db.config.DB;

public class DaoFactory {

    public static ProdutoDao createProductDao() {
        return new ProdutoDaoimpl(DB.getConn());
    }
}
