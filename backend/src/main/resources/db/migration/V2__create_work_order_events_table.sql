CREATE TABLE work_order_events (
    id BIGSERIAL PRIMARY KEY,
    work_order_id BIGINT NOT NULL,
    event_type VARCHAR(50) NOT NULL,
    previous_value VARCHAR(100),
    new_value VARCHAR(100),
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_work_order_events_work_order FOREIGN KEY (work_order_id) REFERENCES work_orders(id)
);
CREATE INDEX idx_work_order_events_work_order_id ON work_order_events(work_order_id);