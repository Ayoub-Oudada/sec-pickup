import ArrowDownOnSquareIcon from "@heroicons/react/24/solid/ArrowDownOnSquareIcon";
import PlusIcon from "@heroicons/react/24/solid/PlusIcon";
import {
  Box,
  Button,
  Container,
  Stack,
  SvgIcon,
  Typography,
} from "@mui/material";
import { useCallback, useMemo, useState } from "react";
import { Link, useLoaderData } from "react-router-dom";
import CustomTable from "../../components/ui/CustomTable";
import { applyPagination } from "../../utils/apply-pagination";

const useEleves = (page, rowsPerPage, data) => {
  console.log(data);
  return useMemo(() => {
    return applyPagination(data, page, rowsPerPage);
  }, [page, rowsPerPage, data]);
};

const fields = [{label:"id", ident:"id"}, {label:"nom", ident:"nom"}, {label:"cne", ident:"cne"}, {label:"niveau", ident:"niveau"}, {label:"parent", ident:["parent","nom"]}, {label:"rue", ident:["rue","lib_rue"]}];

const Index = () => {
  const data = useLoaderData();
  const [page, setPage] = useState(0);
  const [rowsPerPage, setRowsPerPage] = useState(5);
  const eleves = useEleves(page, rowsPerPage, data);

  const handlePageChange = useCallback((event, value) => {
    setPage(value);
  }, []);

  const handleRowsPerPageChange = useCallback((event) => {
    setRowsPerPage(event.target.value);
  }, []);

  return (
    <>
      <Box>
        <title>Eleves | Devias Kit</title>
      </Box>
      <Box
        component="main"
        sx={{
          flexGrow: 1,
          py: 8,
        }}
      >
        <Container maxWidth="xl">
          <Stack spacing={3}>
            <Stack direction="row" justifyContent="space-between" spacing={4}>
              <Stack spacing={1}>
                <Typography variant="h4">Eleves</Typography>
                <Stack alignItems="center" direction="row" spacing={1}>
                  <Button
                    color="inherit"
                    startIcon={
                      <SvgIcon fontSize="small">
                        <ArrowDownOnSquareIcon />
                      </SvgIcon>
                    }
                  >
                    Export
                  </Button>
                </Stack>
              </Stack>
              <Link to={"/eleves/create"}>
                <Button
                  startIcon={
                    <SvgIcon fontSize="small">
                      <PlusIcon />
                    </SvgIcon>
                  }
                  variant="contained"
                >
                  Add
                </Button>
              </Link>
            </Stack>
            <CustomTable
              fields={fields}
              count={data.length}
              items={eleves}
              onPageChange={handlePageChange}
              onRowsPerPageChange={handleRowsPerPageChange}
              page={page}
              rowsPerPage={rowsPerPage}
              actions={{
                updateActionUrl: (id) => "/eleves/" + id,
                deleteActionUrl: (id) => "/eleves/" + id + "/delete",
              }}
            />
          </Stack>
        </Container>
      </Box>
    </>
  );
};

export default Index;