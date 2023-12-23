import { PencilIcon, TrashIcon } from "@heroicons/react/24/solid";
import {
  Box,
  Button,
  Card,
  SvgIcon,
  Table,
  TableBody,
  TableCell,
  TableHead,
  TablePagination,
  TableRow,
  Typography,
} from "@mui/material";
import PropTypes from "prop-types";
import { Form, Link } from "react-router-dom";
import { Scrollbar } from "../scrollbar";

export const CustomTable = (props) => {
  const {
    count = 0,
    items = [],
    fields,
    onPageChange = () => {},
    onRowsPerPageChange,
    page = 0,
    rowsPerPage = 0,
    actions,
  } = props;

  return (
    <Card>
      <Scrollbar>
        <Box sx={{ minWidth: 800 }}>
          <Table>
            <TableHead>
              <TableRow>
                {fields.map((field, index) => (
                  <TableCell key={index}>{field}</TableCell>
                ))}
                <TableCell>actions</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {items.map((item) => {
                return (
                  <TableRow hover key={item.id}>
                    {fields.map((field, index) => (
                      <TableCell key={index}>
                        <Typography variant="subtitle2">
                          {item[field]}
                        </Typography>
                      </TableCell>
                    ))}
                    <TableCell
                      style={{
                        display: "flex",
                        flexDirection: "row",
                        gap: 5,
                      }}
                    >
                      <Link to={actions.updateActionUrl(item.id)}>
                        <Button variant="contained" color={"warning"}>
                          <SvgIcon fontSize="small">
                            <PencilIcon />
                          </SvgIcon>
                        </Button>
                      </Link>

                      <Form
                        method="delete"
                        action={actions.deleteActionUrl(item.id)}
                        onSubmit={(e) => {
                          if (
                            !confirm(
                              "Please confirm you want to delete this record."
                            )
                          ) {
                            e.preventDefault();
                          }
                        }}
                      >
                        <Button variant="contained" color="error" type="submit">
                          <SvgIcon fontSize="small">
                            <TrashIcon />
                          </SvgIcon>
                        </Button>
                      </Form>
                    </TableCell>
                  </TableRow>
                );
              })}
            </TableBody>
          </Table>
        </Box>
      </Scrollbar>
      <TablePagination
        component="div"
        count={count}
        onPageChange={onPageChange}
        onRowsPerPageChange={onRowsPerPageChange}
        page={page}
        rowsPerPage={rowsPerPage}
        rowsPerPageOptions={[5, 10, 25]}
      />
    </Card>
  );
};

CustomTable.propTypes = {
  count: PropTypes.number,
  items: PropTypes.array,
  fields: PropTypes.array,
  onPageChange: PropTypes.func,
  onRowsPerPageChange: PropTypes.func,
  page: PropTypes.number,
  rowsPerPage: PropTypes.number,
  actions: PropTypes.object,
};

export default CustomTable;
