package com.ats.webapi.repository.storestock;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.GetCurrentStockDetails;
import com.ats.webapi.model.stock.GetCurrentStockAdmin;

public interface GetCurrentStockAdminRepo extends JpaRepository<GetCurrentStockAdmin, Integer> {

	
	@Query(value=" Select\n" + 
			"        coalesce(b.opening_stock_header_id,\n" + 
			"        0) as opening_stock_header_id,\n" + 
			"        coalesce(b.opening_stock_detail_id,\n" + 
			"        0) as opening_stock_detail_id,\n" + 
			"        coalesce(b.reg_opening_stock,\n" + 
			"        0) as reg_opening_stock,\n" + 
			"        a.item_rate1 as sp_opening_stock,\n" + 
			"        a.item_mrp1  as sp_total_purchase,\n" + 
			"        a.id,\n" + 
			"        a.item_id,\n" + 
			"        a.item_name,\n" + 
			"        coalesce(d.grn_grn_qty,\n" + 
			"        0) as reg_total_grn_gvn,\n" + 
			"        coalesce(c.reg,\n" + 
			"        0) as reg_total_purchase,\n" + 
			"        coalesce(e.reg,\n" + 
			"        0) as reg_total_sell,\n" + 
			"        coalesce(e.sp,\n" + 
			"        0) as sp_total_sell,\n" + 
			"        coalesce(f.re_order_qty,\n" + 
			"        0) re_order_qty,\n" + 
			"        coalesce(coalesce(((b.reg_opening_stock+c.reg)-(d.grn_grn_qty+e.reg)),\n" + 
			"        ((c.reg)-(d.grn_grn_qty+e.reg))),\n" + 
			"        0) current_reg_stock,\n" + 
			"        coalesce(coalesce(((b.sp_opening_stock+c.sp)-(e.sp)),\n" + 
			"        ((c.sp)-(e.sp))),\n" + 
			"        0) current_sp_stock, COALESCE(e.fr_id, b.fr_id,0) AS fr_id    \n" + 
			"    from\n" + 
			"        (select\n" + 
			"            * \n" + 
			"        from\n" + 
			"            m_item \n" + 
			"        where\n" + 
			"            m_item.id IN (:itemList ) \n" + 
			"        order By\n" + 
			"            item_name ASC) a  \n" + 
			"    LEFT JOIN\n" + 
			"        (\n" + 
			"            SELECT\n" + 
			"                m_fr_opening_stock_detail.*,\n" + 
			"            m_fr_opening_stock_header.fr_id\n" + 
			"            FROM\n" + 
			"                m_fr_opening_stock_detail,\n" + 
			"            m_fr_opening_stock_header\n" + 
			"            WHERE\n" + 
			"            m_fr_opening_stock_header.opening_stock_header_id=m_fr_opening_stock_detail.opening_stock_header_id AND\n" + 
			"                m_fr_opening_stock_detail.opening_stock_header_id  IN(\n" + 
			"                    SELECT\n" + 
			"                        m_fr_opening_stock_header.opening_stock_header_id \n" + 
			"                    FROM\n" + 
			"                        m_fr_opening_stock_header \n" + 
			"                    WHERE\n" + 
			"                        m_fr_opening_stock_header.fr_id=:frId   \n" + 
			"                        AND m_fr_opening_stock_header.month=:currentMonth \n" + 
			"                        AND m_fr_opening_stock_header.year=:year \n" + 
			"                        AND m_fr_opening_stock_header.cat_id=:catId\n" + 
			"                ) \n" + 
			"            ) b \n" + 
			"                on a.id=b.item_id    \n" + 
			"        LEFT JOIN\n" + 
			"            (\n" + 
			"                SELECT\n" + 
			"                    t_bill_detail.item_id,\n" + 
			"                    COALESCE(SUM(CASE \n" + 
			"                        WHEN grn_type != 3 THEN bill_qty \n" + 
			"                        ELSE 0 \n" + 
			"                    END),\n" + 
			"                    0) as reg ,\n" + 
			"                    COALESCE(SUM(CASE \n" + 
			"                        WHEN grn_type = 3 THEN bill_qty \n" + 
			"                        ELSE 0 \n" + 
			"                    END),\n" + 
			"                    0) as sp  \n" + 
			"                FROM\n" + 
			"                    t_bill_detail,\n" + 
			"                    t_bill_header \n" + 
			"                WHERE\n" + 
			"                    t_bill_header.bill_no=t_bill_detail.bill_no  \n" + 
			"                    and t_bill_header.fr_id=:frId   \n" + 
			"                    AND t_bill_header.status=2  \n" + 
			"                    AND t_bill_header.bill_date BETWEEN :fromDate AND :toDate \n" + 
			"                group by\n" + 
			"                    t_bill_detail.item_id\n" + 
			"            ) c \n" + 
			"                on a.id=c.item_id    \n" + 
			"        LEFT JOIN\n" + 
			"            (\n" + 
			"                SELECT\n" + 
			"                    t_grn_gvn.item_id,\n" + 
			"                    COALESCE(SUM(t_grn_gvn.grn_gvn_qty),\n" + 
			"                    0) as grn_grn_qty\n" + 
			"                FROM\n" + 
			"                    t_grn_gvn \n" + 
			"                WHERE\n" + 
			"                    t_grn_gvn.fr_id=:frId  \n" + 
			"                    AND t_grn_gvn.grn_gvn_date  BETWEEN :fromDate AND :toDate\n" + 
			"                group by\n" + 
			"                    t_grn_gvn.item_id\n" + 
			"            ) d \n" + 
			"                on a.id=d.item_id  \n" + 
			"        LEFT JOIN\n" + 
			"            (\n" + 
			"                SELECT\n" + 
			"                    t_sell_bill_detail.item_id,\n" + 
			"                    COALESCE(SUM(CASE \n" + 
			"                        WHEN bill_stock_type = 1 THEN qty \n" + 
			"                        ELSE 0 \n" + 
			"                    END),\n" + 
			"                    0) as reg ,\n" + 
			"                    COALESCE(SUM(CASE \n" + 
			"                        WHEN bill_stock_type = 2 THEN qty \n" + 
			"                        ELSE 0 \n" + 
			"                    END),\n" + 
			"                    0) as sp,\n" + 
			"                t_sell_bill_header.fr_id\n" + 
			"                FROM\n" + 
			"                    t_sell_bill_detail, \n" + 
			"                t_sell_bill_header\n" + 
			"                WHERE\n" + 
			"                t_sell_bill_header.sell_bill_no=t_sell_bill_detail.sell_bill_no AND\n" + 
			"                    t_sell_bill_detail.sell_bill_no IN (\n" + 
			"                        SELECT\n" + 
			"                            t_sell_bill_header.sell_bill_no \n" + 
			"                        FROM\n" + 
			"                            t_sell_bill_header \n" + 
			"                        WHERE\n" + 
			"                            t_sell_bill_header.fr_id=:frId  \n" + 
			"                            AND t_sell_bill_header.bill_date BETWEEN :fromDate AND :toDate \n" + 
			"                    ) \n" + 
			"                group by\n" + 
			"                    t_sell_bill_detail.item_id) e \n" + 
			"                        on a.id=e.item_id  \n" + 
			"                LEFT JOIN\n" + 
			"                    (\n" + 
			"                        SELECT\n" + 
			"                            m_fr_item_stock.item_id,\n" + 
			"                            coalesce(m_fr_item_stock.reorder_qty,\n" + 
			"                            0) re_order_qty \n" + 
			"                        from\n" + 
			"                            m_fr_item_stock \n" + 
			"                        WHERE\n" + 
			"                            m_fr_item_stock.type=:type \n" + 
			"                    ) f \n" + 
			"                        on a.id=f.item_id", nativeQuery=true)
	List<GetCurrentStockAdmin> getCurrentAdminStock(@Param("currentMonth")int currentMonth,@Param("year") int year,@Param("frId") int frId,@Param("catId") int catId,@Param("fromDate") String fromDate,
			@Param("toDate") String toDate,@Param("type") int type, @Param("itemList") List<Integer> itemList);
}
