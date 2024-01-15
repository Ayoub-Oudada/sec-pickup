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

const useParents = (page, rowsPerPage, data) => {
  console.log(data);
  return useMemo(() => {
    return applyPagination(data, page, rowsPerPage);
  }, [page, rowsPerPage, data]);
};

const fields = [{label:"id", ident:"id"}, {label:"email", ident:"email"}, {label:"nom", ident:"nom"}, { label:"prenom", ident:"prenom"}, {label:"cni", ident:"cni"}, {label:"tph",ident:"cni"}];

const Index = () => {
  const data = useLoaderData();
  const [page, setPage] = useState(0);
  const [rowsPerPage, setRowsPerPage] = useState(5);
  const parents = useParents(page, rowsPerPage, data);

  const handlePageChange = useCallback((event, value) => {
    setPage(value);
  }, []);

  const handleRowsPerPageChange = useCallback((event) => {
    setRowsPerPage(event.target.value);
  }, []);

  return (
    <>
      <Box>
        <title>Parents | Devias Kit</title>
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
                <Typography variant="h4">Parents</Typography>
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
              <Link to={"/parents/create"}>
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
              items={parents}
              onPageChange={handlePageChange}
              onRowsPerPageChange={handleRowsPerPageChange}
              page={page}
              rowsPerPage={rowsPerPage}
              actions={{
                updateActionUrl: (id) => "/parents/" + id,
                deleteActionUrl: (id) => "/parents/" + id + "/delete",
              }}
            />
          </Stack>
        </Container>
      </Box>
    </>
  );
};

export default Index;
