DELETE FROM usr;

BEGIN;
-- protect against concurrent inserts while you update the counter
        LOCK TABLE usr IN EXCLUSIVE MODE;
-- Update the sequence
SELECT setval(pg_get_serial_sequence('usr', 'id'), COALESCE((SELECT MAX(id)+1 FROM usr), 1), false);
COMMIT;
