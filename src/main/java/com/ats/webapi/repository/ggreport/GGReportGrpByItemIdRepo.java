package com.ats.webapi.repository.ggreport;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.grngvnreport.GGReportGrpByItemId;

public interface GGReportGrpByItemIdRepo extends JpaRepository<GGReportGrpByItemId, Integer> {

	@Query(value =  " SELECT\n" + 
			"        t_grn_gvn.item_id,\n" + 
			"        i.item_name,\n" + 
			"        t_grn_gvn_header.is_grn,\n" + 
			"        t_grn_gvn_header.fr_id,\n" + 
			"        sum(t_grn_gvn.apr_grand_total)as apr_grand_total,\n" + 
			"        sum(t_grn_gvn.grn_gvn_amt) as total_amt,\n" + 
			"        m_franchisee.fr_name,\n" + 
			"        sum(t_grn_gvn.grn_gvn_qty) as req_qty,\n" + 
			"        sum(t_grn_gvn.apr_qty_acc) as apr_qty      \n" + 
			"    from\n" + 
			"        t_grn_gvn_header,\n" + 
			"        m_franchisee,\n" + 
			"        t_grn_gvn,\n" + 
			"        m_item i     \n" + 
			"    WHERE\n" + 
			"        m_franchisee.fr_id=t_grn_gvn_header.fr_id          \n" + 
			"        AND t_grn_gvn_header.grngvn_date BETWEEN :fromDate and :toDate         \n" + 
			"        AND t_grn_gvn_header.is_grn IN (\n" + 
			"          :isGrn      \n" + 
			"        )          \n" + 
			"        AND t_grn_gvn_header.grn_gvn_header_id=t_grn_gvn.grn_gvn_header_id          \n" + 
			"        AND  t_grn_gvn.fr_id=m_franchisee.fr_id     AND t_grn_gvn_header.fr_id IN(:frIdList) AND i.item_grp1 IN(:catIdList)   \n" + 
			"        AND t_grn_gvn.item_id=i.id  and t_grn_gvn.cat_id!=5 \n" + 
			"    GROUP by\n" + 
			"        t_grn_gvn.item_id\n" + 
			"        \n" + 
			"        UNION ALL\n" + 
			"        \n" + 
			"        SELECT\n" + 
			"        t_grn_gvn.item_id,\n" + 
			"        s.sp_name as item_name,\n" + 
			"        t_grn_gvn_header.is_grn,\n" + 
			"        t_grn_gvn_header.fr_id,\n" + 
			"        sum(t_grn_gvn.apr_grand_total)as apr_grand_total,\n" + 
			"        sum(t_grn_gvn.grn_gvn_amt) as total_amt,\n" + 
			"        m_franchisee.fr_name,\n" + 
			"        sum(t_grn_gvn.grn_gvn_qty) as req_qty,\n" + 
			"        sum(t_grn_gvn.apr_qty_acc) as apr_qty      \n" + 
			"    from\n" + 
			"        t_grn_gvn_header,\n" + 
			"        m_franchisee,\n" + 
			"        t_grn_gvn,\n" + 
			"        m_sp_cake s    \n" + 
			"    WHERE\n" + 
			"        m_franchisee.fr_id=t_grn_gvn_header.fr_id          \n" + 
			"        AND t_grn_gvn_header.grngvn_date BETWEEN :fromDate and :toDate          \n" + 
			"        AND t_grn_gvn_header.is_grn IN (\n" + 
			"           :isGrn     \n" + 
			"        )          \n" + 
			"        AND t_grn_gvn_header.grn_gvn_header_id=t_grn_gvn.grn_gvn_header_id          \n" + 
			"        AND  t_grn_gvn.fr_id=m_franchisee.fr_id    AND t_grn_gvn_header.fr_id IN(:frIdList)     \n" + 
			"        AND t_grn_gvn.item_id=s.sp_id  and t_grn_gvn.cat_id=5  and t_grn_gvn.cat_id IN(:catIdList) \n" + 
			"    GROUP by\n" + 
			"        t_grn_gvn.item_id", nativeQuery = true)

	List<GGReportGrpByItemId> getGGReportGrpByFrIdSelFr(@Param("fromDate") String fromDate,
			@Param("toDate") String toDate, @Param("isGrn") List<String> isGrn,
			@Param("frIdList") List<String> frIdList, @Param("catIdList") List<String> catIdList);

	@Query(value = " SELECT\n" + 
			"        t_grn_gvn.item_id,\n" + 
			"        i.item_name,\n" + 
			"        t_grn_gvn_header.is_grn,\n" + 
			"        t_grn_gvn_header.fr_id,\n" + 
			"        sum(t_grn_gvn.apr_grand_total)as apr_grand_total,\n" + 
			"        sum(t_grn_gvn.grn_gvn_amt) as total_amt,\n" + 
			"        m_franchisee.fr_name,\n" + 
			"        sum(t_grn_gvn.grn_gvn_qty) as req_qty,\n" + 
			"        sum(t_grn_gvn.apr_qty_acc) as apr_qty      \n" + 
			"    from\n" + 
			"        t_grn_gvn_header,\n" + 
			"        m_franchisee,\n" + 
			"        t_grn_gvn,\n" + 
			"        m_item i     \n" + 
			"    WHERE\n" + 
			"        m_franchisee.fr_id=t_grn_gvn_header.fr_id          \n" + 
			"        AND t_grn_gvn_header.grngvn_date BETWEEN :fromDate and :toDate         \n" + 
			"        AND t_grn_gvn_header.is_grn IN (\n" + 
			"          :isGrn      \n" + 
			"        )          \n" + 
			"        AND t_grn_gvn_header.grn_gvn_header_id=t_grn_gvn.grn_gvn_header_id          \n" + 
			"        AND  t_grn_gvn.fr_id=m_franchisee.fr_id     \n" + 
			"        AND t_grn_gvn.item_id=i.id and t_grn_gvn.cat_id!=5 and   i.item_grp1 IN(:catIdList)\n" + 
			"    GROUP by\n" + 
			"        t_grn_gvn.item_id\n" + 
			"        \n" + 
			"        UNION ALL\n" + 
			"        \n" + 
			"        SELECT\n" + 
			"        t_grn_gvn.item_id,\n" + 
			"        s.sp_name as item_name,\n" + 
			"        t_grn_gvn_header.is_grn,\n" + 
			"        t_grn_gvn_header.fr_id,\n" + 
			"        sum(t_grn_gvn.apr_grand_total)as apr_grand_total,\n" + 
			"        sum(t_grn_gvn.grn_gvn_amt) as total_amt,\n" + 
			"        m_franchisee.fr_name,\n" + 
			"        sum(t_grn_gvn.grn_gvn_qty) as req_qty,\n" + 
			"        sum(t_grn_gvn.apr_qty_acc) as apr_qty      \n" + 
			"    from\n" + 
			"        t_grn_gvn_header,\n" + 
			"        m_franchisee,\n" + 
			"        t_grn_gvn,\n" + 
			"        m_sp_cake s    \n" + 
			"    WHERE\n" + 
			"        m_franchisee.fr_id=t_grn_gvn_header.fr_id          \n" + 
			"        AND t_grn_gvn_header.grngvn_date BETWEEN :fromDate and :toDate          \n" + 
			"        AND t_grn_gvn_header.is_grn IN (\n" + 
			"           :isGrn     \n" + 
			"        )          \n" + 
			"        AND t_grn_gvn_header.grn_gvn_header_id=t_grn_gvn.grn_gvn_header_id          \n" + 
			"        AND  t_grn_gvn.fr_id=m_franchisee.fr_id \n" + 
			"        AND t_grn_gvn.item_id=s.sp_id  and t_grn_gvn.cat_id=5 and t_grn_gvn.cat_id IN(:catIdList)\n" + 
			"    GROUP by\n" + 
			"        t_grn_gvn.item_id", nativeQuery = true)

	List<GGReportGrpByItemId> getGGReportGrpByItemIdSelFr(@Param("fromDate") String fromDate,
			@Param("toDate") String toDate, @Param("isGrn") List<String> isGrn,
			@Param("catIdList") List<String> catIdList);

	@Query(value =  " SELECT\n" + 
			"        t_grn_gvn.item_id,\n" + 
			"        i.item_name,\n" + 
			"        t_grn_gvn_header.is_grn,\n" + 
			"        t_grn_gvn_header.fr_id,\n" + 
			"        sum(t_grn_gvn.apr_grand_total)as apr_grand_total,\n" + 
			"        sum(t_grn_gvn.grn_gvn_amt) as total_amt,\n" + 
			"        m_franchisee.fr_name,\n" + 
			"        sum(t_grn_gvn.grn_gvn_qty) as req_qty,\n" + 
			"        sum(t_grn_gvn.apr_qty_acc) as apr_qty      \n" + 
			"    from\n" + 
			"        t_grn_gvn_header,\n" + 
			"        m_franchisee,\n" + 
			"        t_grn_gvn,\n" + 
			"        m_item i     \n" + 
			"    WHERE\n" + 
			"        m_franchisee.fr_id=t_grn_gvn_header.fr_id          \n" + 
			"        AND t_grn_gvn_header.grngvn_date BETWEEN :fromDate and :toDate         \n" + 
			"        AND t_grn_gvn_header.is_grn IN (\n" + 
			"          :isGrn      \n" + 
			"        )          \n" + 
			"        AND t_grn_gvn_header.grn_gvn_header_id=t_grn_gvn.grn_gvn_header_id          \n" + 
			"        AND  t_grn_gvn.fr_id=m_franchisee.fr_id     AND t_grn_gvn_header.fr_id IN(:frIdList)    \n" + 
			"        AND t_grn_gvn.item_id=i.id  and t_grn_gvn.cat_id!=5 \n" + 
			"    GROUP by\n" + 
			"        t_grn_gvn.item_id\n" + 
			"        \n" + 
			"        UNION ALL\n" + 
			"        \n" + 
			"        SELECT\n" + 
			"        t_grn_gvn.item_id,\n" + 
			"        s.sp_name as item_name,\n" + 
			"        t_grn_gvn_header.is_grn,\n" + 
			"        t_grn_gvn_header.fr_id,\n" + 
			"        sum(t_grn_gvn.apr_grand_total)as apr_grand_total,\n" + 
			"        sum(t_grn_gvn.grn_gvn_amt) as total_amt,\n" + 
			"        m_franchisee.fr_name,\n" + 
			"        sum(t_grn_gvn.grn_gvn_qty) as req_qty,\n" + 
			"        sum(t_grn_gvn.apr_qty_acc) as apr_qty      \n" + 
			"    from\n" + 
			"        t_grn_gvn_header,\n" + 
			"        m_franchisee,\n" + 
			"        t_grn_gvn,\n" + 
			"        m_sp_cake s    \n" + 
			"    WHERE\n" + 
			"        m_franchisee.fr_id=t_grn_gvn_header.fr_id          \n" + 
			"        AND t_grn_gvn_header.grngvn_date BETWEEN :fromDate and :toDate          \n" + 
			"        AND t_grn_gvn_header.is_grn IN (\n" + 
			"           :isGrn     \n" + 
			"        )          \n" + 
			"        AND t_grn_gvn_header.grn_gvn_header_id=t_grn_gvn.grn_gvn_header_id          \n" + 
			"        AND  t_grn_gvn.fr_id=m_franchisee.fr_id    AND t_grn_gvn_header.fr_id IN(:frIdList)     \n" + 
			"        AND t_grn_gvn.item_id=s.sp_id  and t_grn_gvn.cat_id=5 \n" + 
			"    GROUP by\n" +    
			"        t_grn_gvn.item_id", nativeQuery = true)

	List<GGReportGrpByItemId> getGGReportGrpByItemId(@Param("fromDate") String fromDate, @Param("toDate") String toDate,
			@Param("isGrn") List<String> isGrn, @Param("frIdList") List<String> frIdList);

	@Query(value = " SELECT\n" + 
			"        t_grn_gvn.item_id,\n" + 
			"        i.item_name,\n" + 
			"        t_grn_gvn_header.is_grn,\n" + 
			"        t_grn_gvn_header.fr_id,\n" + 
			"        sum(t_grn_gvn.apr_grand_total)as apr_grand_total,\n" + 
			"        sum(t_grn_gvn.grn_gvn_amt) as total_amt,\n" + 
			"        m_franchisee.fr_name,\n" + 
			"        sum(t_grn_gvn.grn_gvn_qty) as req_qty,\n" + 
			"        sum(t_grn_gvn.apr_qty_acc) as apr_qty      \n" + 
			"    from\n" + 
			"        t_grn_gvn_header,\n" + 
			"        m_franchisee,\n" + 
			"        t_grn_gvn,\n" + 
			"        m_item i     \n" + 
			"    WHERE\n" + 
			"        m_franchisee.fr_id=t_grn_gvn_header.fr_id          \n" + 
			"        AND t_grn_gvn_header.grngvn_date BETWEEN :fromDate and :toDate         \n" + 
			"        AND t_grn_gvn_header.is_grn IN (\n" + 
			"          :isGrn      \n" + 
			"        )          \n" + 
			"        AND t_grn_gvn_header.grn_gvn_header_id=t_grn_gvn.grn_gvn_header_id          \n" + 
			"        AND  t_grn_gvn.fr_id=m_franchisee.fr_id      \n" + 
			"        AND t_grn_gvn.item_id=i.id  and t_grn_gvn.cat_id!=5 \n" + 
			"    GROUP by\n" + 
			"        t_grn_gvn.item_id\n" + 
			"        \n" + 
			"        UNION ALL\n" + 
			"        \n" + 
			"        SELECT\n" + 
			"        t_grn_gvn.item_id,\n" + 
			"        s.sp_name as item_name,\n" + 
			"        t_grn_gvn_header.is_grn,\n" + 
			"        t_grn_gvn_header.fr_id,\n" + 
			"        sum(t_grn_gvn.apr_grand_total)as apr_grand_total,\n" + 
			"        sum(t_grn_gvn.grn_gvn_amt) as total_amt,\n" + 
			"        m_franchisee.fr_name,\n" + 
			"        sum(t_grn_gvn.grn_gvn_qty) as req_qty,\n" + 
			"        sum(t_grn_gvn.apr_qty_acc) as apr_qty      \n" + 
			"    from\n" + 
			"        t_grn_gvn_header,\n" + 
			"        m_franchisee,\n" + 
			"        t_grn_gvn,\n" + 
			"        m_sp_cake s    \n" + 
			"    WHERE\n" + 
			"        m_franchisee.fr_id=t_grn_gvn_header.fr_id          \n" + 
			"        AND t_grn_gvn_header.grngvn_date BETWEEN :fromDate and :toDate          \n" + 
			"        AND t_grn_gvn_header.is_grn IN (\n" + 
			"           :isGrn     \n" + 
			"        )          \n" + 
			"        AND t_grn_gvn_header.grn_gvn_header_id=t_grn_gvn.grn_gvn_header_id          \n" + 
			"        AND  t_grn_gvn.fr_id=m_franchisee.fr_id      \n" + 
			"        AND t_grn_gvn.item_id=s.sp_id  and t_grn_gvn.cat_id=5 \n" + 
			"    GROUP by\n" + 
			"        t_grn_gvn.item_id", nativeQuery = true)

	List<GGReportGrpByItemId> getGGReportGrpByFrIdSelFrAllFr(@Param("fromDate") String fromDate,
			@Param("toDate") String toDate, @Param("isGrn") List<String> isGrn);

	@Query(value="  SELECT\n" + 
			"        t_grn_gvn.item_id,\n" + 
			"        i.item_name,\n" + 
			"        t_grn_gvn_header.is_grn,\n" + 
			"        t_grn_gvn_header.fr_id,\n" + 
			"        sum(t_grn_gvn.apr_grand_total)as apr_grand_total,\n" + 
			"        sum(t_grn_gvn.grn_gvn_amt) as total_amt,\n" + 
			"        m_franchisee.fr_name,\n" + 
			"        sum(t_grn_gvn.grn_gvn_qty) as req_qty,\n" + 
			"        sum(t_grn_gvn.apr_qty_acc) as apr_qty           \n" + 
			"    from\n" + 
			"        t_grn_gvn_header,\n" + 
			"        m_franchisee,\n" + 
			"        t_grn_gvn,\n" + 
			"        m_item i          \n" + 
			"    WHERE\n" + 
			"        m_franchisee.fr_id=t_grn_gvn_header.fr_id                   \n" + 
			"        AND t_grn_gvn_header.grngvn_date BETWEEN :fromDate and :toDate               \n" + 
			"        AND t_grn_gvn_header.is_grn IN (:isGrn)                   \n" + 
			"        AND t_grn_gvn_header.grn_gvn_header_id=t_grn_gvn.grn_gvn_header_id                   \n" + 
			"        AND  t_grn_gvn.fr_id=m_franchisee.fr_id     \n" + 
			"        AND t_grn_gvn_header.fr_id IN(:frIdList) \n" + 
			"        AND i.item_grp1 IN(:catIdList)   \n" + 
			"        AND i.item_grp2 IN (:subCatId)\n" + 
			"        AND t_grn_gvn.item_id=i.id  \n" + 
			"        and t_grn_gvn.cat_id!=5      \n" + 
			"    GROUP by\n" + 
			"        t_grn_gvn.item_id                  \n" + 
			"    UNION\n" + 
			"    ALL                  SELECT\n" + 
			"        t_grn_gvn.item_id,\n" + 
			"        s.sp_name as item_name,\n" + 
			"        t_grn_gvn_header.is_grn,\n" + 
			"        t_grn_gvn_header.fr_id,\n" + 
			"        sum(t_grn_gvn.apr_grand_total)as apr_grand_total,\n" + 
			"        sum(t_grn_gvn.grn_gvn_amt) as total_amt,\n" + 
			"        m_franchisee.fr_name,\n" + 
			"        sum(t_grn_gvn.grn_gvn_qty) as req_qty,\n" + 
			"        sum(t_grn_gvn.apr_qty_acc) as apr_qty           \n" + 
			"    from\n" + 
			"        t_grn_gvn_header,\n" + 
			"        m_franchisee,\n" + 
			"        t_grn_gvn,\n" + 
			"        m_sp_cake s         \n" + 
			"    WHERE\n" + 
			"        m_franchisee.fr_id=t_grn_gvn_header.fr_id                   \n" + 
			"        AND t_grn_gvn_header.grngvn_date BETWEEN :fromDate and :toDate                    \n" + 
			"        AND t_grn_gvn_header.is_grn IN (:isGrn)                  \n" + 
			"        AND t_grn_gvn_header.grn_gvn_header_id=t_grn_gvn.grn_gvn_header_id                   \n" + 
			"        AND  t_grn_gvn.fr_id=m_franchisee.fr_id    \n" + 
			"        AND t_grn_gvn_header.fr_id IN(:frIdList)              \n" + 
			"        AND t_grn_gvn.item_id=s.sp_id  \n" + 
			"        and t_grn_gvn.cat_id=5  \n" + 
			"        and t_grn_gvn.cat_id IN(:catIdList)      \n" + 
			"    GROUP by\n" + 
			"        t_grn_gvn.item_id",nativeQuery=true)
	List<GGReportGrpByItemId> getGGReportGrpBySubCatId(@Param("frIdList") List<String> frIdList, @Param("fromDate") String fromDate, 
			@Param("toDate") String toDate, @Param("isGrn") List<String> isGrn, @Param("catIdList") List<String> catIdList, 
			@Param("subCatId") List<String> subCatId);

}
