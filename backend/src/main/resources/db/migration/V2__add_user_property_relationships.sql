ALTER TABLE properties
    ADD COLUMN owner_id BIGINT NULL;

UPDATE properties p
JOIN users u ON u.email = p.owner_email
SET p.owner_id = u.id
WHERE p.owner_id IS NULL;

ALTER TABLE properties
    ADD CONSTRAINT fk_properties_owner
    FOREIGN KEY (owner_id) REFERENCES users(id)
    ON DELETE CASCADE;

CREATE INDEX idx_properties_owner_id ON properties(owner_id);

ALTER TABLE bookings
    ADD COLUMN user_id BIGINT NULL;

UPDATE bookings b
JOIN users u ON u.email = b.user_email
SET b.user_id = u.id
WHERE b.user_id IS NULL;

ALTER TABLE bookings
    ADD CONSTRAINT fk_bookings_user
    FOREIGN KEY (user_id) REFERENCES users(id)
    ON DELETE CASCADE;

ALTER TABLE bookings
    ADD CONSTRAINT fk_bookings_property
    FOREIGN KEY (property_id) REFERENCES properties(id)
    ON DELETE CASCADE;

CREATE INDEX idx_bookings_user_id ON bookings(user_id);
CREATE INDEX idx_bookings_property_id ON bookings(property_id);
