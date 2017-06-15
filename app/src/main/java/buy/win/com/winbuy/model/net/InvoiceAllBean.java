package buy.win.com.winbuy.model.net;

import java.util.List;

/**
 * Created by BUTTON on 2017-06-15.
 */

public class InvoiceAllBean extends BaseBean {


    private List<InvoiceBean> invoice;

    public void setInvoice(List<InvoiceBean> invoice) {
        this.invoice = invoice;
    }

    public List<InvoiceBean> getInvoice() {
        return invoice;
    }

    public static class InvoiceBean {
        /**
         * content : 图书
         * id : 1
         */

        private String content;
        private int id;

        public void setContent(String content) {
            this.content = content;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getContent() {
            return content;
        }

        public int getId() {
            return id;
        }
    }
}
