
CREATE TABLE tenants (
    tenant_id    BIGSERIAL PRIMARY KEY,
    shop_name    VARCHAR(255) NOT NULL,
    phone        VARCHAR(15)  NOT NULL UNIQUE,
    trade_category VARCHAR(50) NOT NULL
                 CHECK (trade_category IN (
                     'kirana', 'pharmacy', 'auto_parts',
                     'hardware', 'restaurant', 'other'
                 )),
    plan_type    VARCHAR(20)  NOT NULL DEFAULT 'free'
                 CHECK (plan_type IN ('free', 'pro', 'business')),
    gstin        VARCHAR(15),
    language     VARCHAR(5)   NOT NULL DEFAULT 'hi'
                 CHECK (language IN ('hi', 'kn', 'en')),
    is_active    BOOLEAN      NOT NULL DEFAULT TRUE,
    created_at   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE users (
    user_id      BIGSERIAL PRIMARY KEY,
    tenant_id    BIGINT       NOT NULL
                 REFERENCES tenants(tenant_id)
                 ON DELETE CASCADE,
    name         VARCHAR(255) NOT NULL,
    phone        VARCHAR(15)  NOT NULL,
    role         VARCHAR(20)  NOT NULL DEFAULT 'cashier'
                 CHECK (role IN (
                     'owner', 'manager',
                     'cashier', 'delivery'
                 )),
    is_active    BOOLEAN      NOT NULL DEFAULT TRUE,
    created_at   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (tenant_id, phone)
);


CREATE TABLE customers (
    customer_id         BIGSERIAL PRIMARY KEY,
    tenant_id           BIGINT        NOT NULL
                        REFERENCES tenants(tenant_id)
                        ON DELETE CASCADE,
    customer_name       VARCHAR(255)  NOT NULL,
    phone               VARCHAR(15),
    address             TEXT,
    outstanding_balance DECIMAL(12,2) NOT NULL DEFAULT 0.00,
    created_at          TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE parties (
    party_id     BIGSERIAL PRIMARY KEY,
    tenant_id    BIGINT       NOT NULL
                 REFERENCES tenants(tenant_id)
                 ON DELETE CASCADE,
    party_name   VARCHAR(255) NOT NULL,
    phone        VARCHAR(15),
    gstin        VARCHAR(15),
    created_at   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE items (
    item_id           BIGSERIAL PRIMARY KEY,
    tenant_id         BIGINT        NOT NULL
                      REFERENCES tenants(tenant_id)
                      ON DELETE CASCADE,
    item_name         VARCHAR(255)  NOT NULL,
    category          VARCHAR(100),
    hsn_code          VARCHAR(20),
    mrp               DECIMAL(12,2),
    sale_price        DECIMAL(12,2) NOT NULL,
    purchase_price    DECIMAL(12,2),
    gst_rate          DECIMAL(5,2)  NOT NULL DEFAULT 0
                      CHECK (gst_rate IN (0, 5, 12, 18, 28)),
    current_stock     DECIMAL(12,3) NOT NULL DEFAULT 0,
    low_stock_limit   DECIMAL(12,3) NOT NULL DEFAULT 10,
    unit              VARCHAR(20)   NOT NULL DEFAULT 'piece',
    is_active         BOOLEAN       NOT NULL DEFAULT TRUE,
    created_at        TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE stock (
    stock_id             BIGSERIAL PRIMARY KEY,
    tenant_id            BIGINT        NOT NULL
                         REFERENCES tenants(tenant_id)
                         ON DELETE CASCADE,
    item_id              BIGINT        NOT NULL
                         REFERENCES items(item_id),
    purchase_item_id     BIGINT,
    batch_number         VARCHAR(100),
    expiry_date          DATE,
    quantity_received    DECIMAL(12,3) NOT NULL,
    quantity_remaining   DECIMAL(12,3) NOT NULL,
    created_at           TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CHECK (quantity_remaining >= 0),
    CHECK (quantity_remaining <= quantity_received)
);


CREATE TABLE bills (
    bill_id            BIGSERIAL PRIMARY KEY,
    tenant_id          BIGINT        NOT NULL
                       REFERENCES tenants(tenant_id)
                       ON DELETE CASCADE,
    customer_id        BIGINT
                       REFERENCES customers(customer_id),
    created_by         BIGINT
                       REFERENCES users(user_id),
    bill_number        VARCHAR(50)   NOT NULL,
    bill_date          TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    subtotal           DECIMAL(12,2) NOT NULL,
    gst_amount         DECIMAL(12,2) NOT NULL DEFAULT 0,
    discount           DECIMAL(12,2) NOT NULL DEFAULT 0,
    total_amount       DECIMAL(12,2) NOT NULL,
    payment_status     VARCHAR(20)   NOT NULL DEFAULT 'unpaid'
                       CHECK (payment_status IN (
                           'paid', 'unpaid', 'partial'
                       )),
    payment_method     VARCHAR(20)
                       CHECK (payment_method IN (
                           'cash', 'upi', 'card',
                           'credit', 'cheque'
                       )),
    whatsapp_sent      BOOLEAN       NOT NULL DEFAULT FALSE,
    whatsapp_delivered BOOLEAN       NOT NULL DEFAULT FALSE,
    created_at         TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (tenant_id, bill_number)
);


CREATE TABLE bill_items (
    bill_item_id   BIGSERIAL PRIMARY KEY,
    bill_id        BIGINT        NOT NULL
                   REFERENCES bills(bill_id)
                   ON DELETE CASCADE,
    item_id        BIGINT        NOT NULL
                   REFERENCES items(item_id),
    stock_id       BIGINT
                   REFERENCES stock(stock_id),
    quantity       DECIMAL(12,3) NOT NULL,
    unit_price     DECIMAL(12,2) NOT NULL,
    gst_rate       DECIMAL(5,2)  NOT NULL DEFAULT 0,
    gst_amount     DECIMAL(12,2) NOT NULL DEFAULT 0,
    line_total     DECIMAL(12,2) NOT NULL,
    CHECK (quantity > 0),
    CHECK (line_total >= 0)
);


CREATE TABLE purchases (
    purchase_id        BIGSERIAL PRIMARY KEY,
    tenant_id          BIGINT        NOT NULL
                       REFERENCES tenants(tenant_id)
                       ON DELETE CASCADE,
    party_id           BIGINT
                       REFERENCES parties(party_id),
    vendor_invoice_no  VARCHAR(100),
    purchase_date      TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    subtotal           DECIMAL(12,2) NOT NULL,
    gst_amount         DECIMAL(12,2) NOT NULL DEFAULT 0,
    total_amount       DECIMAL(12,2) NOT NULL,
    payment_status     VARCHAR(20)   NOT NULL DEFAULT 'unpaid'
                       CHECK (payment_status IN (
                           'paid', 'unpaid', 'partial'
                       )),
    created_at         TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE purchase_items (
    purchase_item_id   BIGSERIAL PRIMARY KEY,
    purchase_id        BIGINT        NOT NULL
                       REFERENCES purchases(purchase_id)
                       ON DELETE CASCADE,
    item_id            BIGINT        NOT NULL
                       REFERENCES items(item_id),
    quantity           DECIMAL(12,3) NOT NULL,
    unit_price         DECIMAL(12,2) NOT NULL,
    gst_rate           DECIMAL(5,2)  NOT NULL DEFAULT 0,
    CHECK (quantity > 0)
);


CREATE TABLE transactions (
    transaction_id    BIGSERIAL PRIMARY KEY,
    tenant_id         BIGINT        NOT NULL
                      REFERENCES tenants(tenant_id)
                      ON DELETE CASCADE,
    reference_id      BIGINT        NOT NULL,
    reference_type    VARCHAR(20)   NOT NULL
                      CHECK (reference_type IN (
                          'bill', 'purchase',
                          'payment', 'expense'
                      )),
    amount            DECIMAL(12,2) NOT NULL,
    direction         VARCHAR(5)    NOT NULL
                      CHECK (direction IN ('in', 'out')),
    payment_method    VARCHAR(20)
                      CHECK (payment_method IN (
                          'cash', 'upi', 'card',
                          'credit', 'cheque'
                      )),
    transaction_date  TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    note              TEXT
);


CREATE TABLE payments (
    payment_id      BIGSERIAL PRIMARY KEY,
    tenant_id       BIGINT        NOT NULL
                    REFERENCES tenants(tenant_id)
                    ON DELETE CASCADE,
    customer_id     BIGINT
                    REFERENCES customers(customer_id),
    bill_id         BIGINT
                    REFERENCES bills(bill_id),
    amount          DECIMAL(12,2) NOT NULL,
    payment_method  VARCHAR(20)   NOT NULL
                    CHECK (payment_method IN (
                        'cash', 'upi', 'card', 'cheque'
                    )),
    payment_date    TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    recorded_by     BIGINT
                    REFERENCES users(user_id)
);

CREATE TABLE expenses (
    expense_id      BIGSERIAL PRIMARY KEY,
    tenant_id       BIGINT        NOT NULL
                    REFERENCES tenants(tenant_id)
                    ON DELETE CASCADE,
    recorded_by     BIGINT
                    REFERENCES users(user_id),
    expense_name    VARCHAR(255)  NOT NULL,
    category        VARCHAR(100)  NOT NULL
                    CHECK (category IN (
                        'rent', 'electricity', 'salary',
                        'transport', 'maintenance',
                        'marketing', 'other'
                    )),
    amount          DECIMAL(12,2) NOT NULL,
    payment_method  VARCHAR(20)
                    CHECK (payment_method IN (
                        'cash', 'upi', 'card', 'cheque'
                    )),
    expense_date    DATE          NOT NULL,
    note            TEXT,
    created_at      TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE stock
    ADD CONSTRAINT fk_stock_purchase_item
    FOREIGN KEY (purchase_item_id)
    REFERENCES purchase_items(purchase_item_id);